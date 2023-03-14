package training_3_0.divA.bfs.maze_38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

enum Direction {
    DOWN, UP, LEFT, RIGHT;
}

class Tuple {
    private int x, y, cnt;
    private Direction d;

    public Tuple(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    public Tuple(int x, int y, int cnt, Direction d) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.d = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return x == tuple.x && y == tuple.y && cnt == tuple.cnt && d == tuple.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, cnt, d);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCnt() {
        return cnt;
    }

    public Direction getD() {
        return d;
    }
}

//ML на 13 тесте, сдана на C++
public class Maze38 {
    private static Queue<Tuple> q;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[][] maze = new int[n + 2][m + 2];
            Arrays.fill(maze[0], 1);
            Arrays.fill(maze[n + 1], 1);
            for (int i = 1; i <= n; ++i) {
                maze[i][0] = 1;
                maze[i][m + 1] = 1;
                line = reader.readLine().split(" ");
                for (int j = 1; j <= m; ++j) {
                    maze[i][j] = Integer.parseInt(line[j - 1]);
                }
            }
            q = new ArrayDeque<>();
            q.offer(new Tuple(1, 1, 0));
            int ans = -1;
            while (!q.isEmpty()) {
                Tuple v = q.poll();
                int x = v.getX();
                int y = v.getY();
                int cnt = v.getCnt();
                Direction d = v.getD();
                int i, j;
                if (d != Direction.DOWN) {
                    i = x;
                    j = y;
                    while (maze[i - 1][j] != 1 && maze[i - 1][j] != 2) {
                        --i;
                    }
                    if (maze[i - 1][j] == 2) {
                        ans = cnt + 1;
                        break;
                    }
                    q.offer(new Tuple(i, j, cnt + 1, Direction.UP));
                }
                if (d != Direction.UP) {
                    i = x;
                    j = y;
                    while (maze[i + 1][j] != 1 && maze[i + 1][j] != 2) {
                        ++i;
                    }
                    if (maze[i + 1][j] == 2) {
                        ans = cnt + 1;
                        break;
                    }
                    q.offer(new Tuple(i, j, cnt + 1, Direction.DOWN));
                }
                if (d != Direction.LEFT) {
                    i = x;
                    j = y;
                    while (maze[i][j + 1] != 1 && maze[i][j + 1] != 2) {
                        ++j;
                    }
                    if (maze[i][j + 1] == 2) {
                        ans = cnt + 1;
                        break;
                    }
                    q.offer(new Tuple(i, j, cnt + 1, Direction.RIGHT));
                }
                if (d != Direction.RIGHT) {
                    i = x;
                    j = y;
                    while (maze[i][j - 1] != 1 && maze[i][j - 1] != 2) {
                        --j;
                    }
                    if (maze[i][j - 1] == 2) {
                        ans = cnt + 1;
                        break;
                    }
                    q.offer(new Tuple(i, j, cnt + 1, Direction.LEFT));
                }
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
