package training_3_0.divB.bfs.fleas_38;

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

public class Fleas38 {
    private static int[][] d;
    private static Queue<Pair> queue;
    private static int n, m;
    private static Pair[] shift = {new Pair(-2, -1), new Pair(-2, 1),
            new Pair(-1, -2), new Pair(-1, 2), new Pair(2, -1),
            new Pair(2, 1), new Pair(1, -2), new Pair(1, 2)};

    private static void add(int x, int y) {
        for (Pair p : shift) {
            int i = x + p.getX();
            int j = y + p.getY();
            if (d[i][j] == -1) {
                queue.offer(new Pair(i, j));
                d[i][j] = d[x][y] + 1;
            }
        }
    }

    private static void populate(){
        Arrays.fill(d[0], -2);
        Arrays.fill(d[1], -2);
        Arrays.fill(d[n + 2], -2);
        Arrays.fill(d[n + 3], -2);
        for (int i = 2; i <= n + 1; ++i) {
            Arrays.fill(d[i], -1);
            d[i][0] = -2;
            d[i][1] = -2;
            d[i][m + 2] = -2;
            d[i][m + 3] = -2;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            n = Integer.parseInt(line[0]);
            m = Integer.parseInt(line[1]);
            int s = Integer.parseInt(line[2]) + 1;
            int t = Integer.parseInt(line[3]) + 1;
            int q = Integer.parseInt(line[4]);
            d = new int[n + 4][m + 4];
            populate();
            List<Pair> qs = new ArrayList<>();
            for (int i = 0; i < q; ++i) {
                line = reader.readLine().split(" ");
                int x = Integer.parseInt(line[0]) + 1;
                int y = Integer.parseInt(line[1]) + 1;
                qs.add(new Pair(x, y));
            }
            queue = new ArrayDeque<>();
            queue.offer(new Pair(s, t));
            d[s][t] = 0;
            while (!queue.isEmpty()) {
                Pair v = queue.poll();
                int x = v.getX();
                int y = v.getY();
                add(x, y);
            }
            int ans = 0;
            for (Pair p : qs) {
                if (d[p.getX()][p.getY()] == -1) {
                    ans = -1;
                    break;
                }
                ans += d[p.getX()][p.getY()];
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
