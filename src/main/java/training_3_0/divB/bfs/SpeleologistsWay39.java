package training_3_0.divB.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

class Tuple {
    private int x, y, z;

    public Tuple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return x == tuple.x && y == tuple.y && z == tuple.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

public class SpeleologistsWay39 {
    private static int[][][] d;
    private static Queue<Tuple> q;
    private static int n;
    private static char[][][] cave;
    private static Tuple[] shift = {new Tuple(-1, 0, 0), new Tuple(1, 0, 0), new Tuple(0, -1, 0),
            new Tuple(0, 1, 0), new Tuple(0, 0, -1), new Tuple(0, 0, 1)};

    private static void add(int x, int y, int z) {
        for (Tuple up : shift) {
            int i = x + up.getX();
            int j = y + up.getY();
            int k = z + up.getZ();
            if (cave[i][j][k] == '.' && d[i][j][k] == -1) {
                q.add(new Tuple(i, j, k));
                d[i][j][k] = d[x][y][z] + 1;
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            n = Integer.parseInt(reader.readLine());
            cave = new char[n + 1][n + 2][n + 2];
            d = new int[n + 1][n + 2][n + 2];
            for (int i = 0; i < n + 2; ++i) {
                Arrays.fill(cave[0][i], '#');
                Arrays.fill(cave[n][i], '#');
            }
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j < n + 2; ++j) {
                    Arrays.fill(d[i][j], -1);
                }
            }
            int sx = 0, sy = 0, sz = 0;
            for (int i = 0; i < n; ++i) {
                reader.readLine();
                for (int j = 1; j <= n; ++j) {
                    cave[i][j][0] = '#';
                    cave[i][j][n + 1] = '#';
                    cave[i][0][j] = '#';
                    cave[i][n + 1][j] = '#';
                    String line = reader.readLine();
                    for (int k = 1; k <= n; ++k) {
                        char c = line.charAt(k - 1);
                        if (c == 'S') {
                            sx = i;
                            sy = j;
                            sz = k;
                        }
                        cave[i][j][k] = c;
                    }
                }
            }
            q = new ArrayDeque<>();
            q.offer(new Tuple(sx, sy, sz));
            d[sx][sy][sz] = 0;
            int ans = 0;
            while (true) {
                Tuple t = q.poll();
                int x = t.getX();
                int y = t.getY();
                int z = t.getZ();
                if (x == 0) {
                    ans = d[x][y][z];
                    break;
                }
                add(x, y, z);
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
