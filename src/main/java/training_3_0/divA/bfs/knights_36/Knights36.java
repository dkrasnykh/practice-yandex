package training_3_0.divA.bfs.knights_36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Knights36 {
    private static Queue<Pair> q;
    private static Pair[] shift = {new Pair(-2, -1), new Pair(-2, 1),
            new Pair(-1, -2), new Pair(-1, 2), new Pair(2, -1),
            new Pair(2, 1), new Pair(1, -2), new Pair(1, 2)};

    public static void populate(int[][] d) {
        List<Integer> ind = new ArrayList<>(Arrays.asList(0, 1, 10, 11));
        for (int i = 2; i < 10; ++i) {
            Arrays.fill(d[i], -1);
            for (Integer j : ind) {
                d[i][j] = -2;
            }
        }
        for (Integer i : ind) {
            Arrays.fill(d[i], -2);
        }
    }

    public static Pair convert(String coordinate) {
        int x = coordinate.charAt(0) - 95;
        int y = coordinate.charAt(1) - 47;
        return new Pair(x, y);
    }

    public static void bfs(int[][] d, Pair coordinate) {
        q = new ArrayDeque<>();
        q.offer(coordinate);
        d[coordinate.getX()][coordinate.getY()] = 0;
        while (!q.isEmpty()) {
            Pair v = q.poll();
            int x = v.getX();
            int y = v.getY();
            for (Pair s : shift) {
                int i = x + s.getX();
                int j = y + s.getY();
                if (d[i][j] == -1) {
                    q.offer(new Pair(i, j));
                    d[i][j] = d[x][y] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            Pair coordinateGreen = convert(line[0]);
            Pair coordinateRed = convert(line[1]);
            int[][] dGreen = new int[12][12];
            int[][] dRed = new int[12][12];
            populate(dGreen);
            populate(dRed);
            bfs(dGreen, coordinateGreen);
            bfs(dRed, coordinateRed);
            int imin = 0;
            int jmin = 0;
            for (int i = 2; i < 10; ++i) {
                for (int j = 2; j < 10; ++j) {
                    if (dGreen[i][i] >= 0 && dGreen[i][j] == dRed[i][j]) {
                        if(imin == 0 && jmin == 0 || dGreen[i][j] < dGreen[imin][jmin]){
                            imin = i;
                            jmin = j;
                        }
                    }
                }
            }
            if(imin == 0 && jmin == 0){
                writer.println(-1);
            } else {
                writer.println(dRed[imin][jmin]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
