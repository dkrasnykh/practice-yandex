package training_3_0.divA.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RoomArea31 {
    private static char[][] maze;
    private static boolean[][] used;
    private static List<int[]> next(Integer i, Integer j) {
        List<int[]> res = new ArrayList<>();
        if (maze[i - 1][j] != '*') {
            res.add(new int[]{i - 1, j});
        }
        if (maze[i][j + 1] != '*') {
            res.add(new int[]{i, j + 1});
        }
        if (maze[i + 1][j] != '*') {
            res.add(new int[]{i + 1, j});
        }
        if (maze[i][j - 1] != '*') {
            res.add(new int[]{i, j - 1});
        }
        return res;
    }

    private static void dfs(int i, int j) {
        used[i][j] = true;
        List<int[]> points = next(i, j);
        for (int[] p : points) {
            if (!used[p[0]][p[1]]) {
                dfs(p[0], p[1]);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            maze = new char[n][n];
            used = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                line = reader.readLine().trim();
                for (int j = 0; j < n; ++j) {
                    maze[i][j] = line.charAt(j);
                }
            }
            String[] input = reader.readLine().split(" ");
            int x = Integer.parseInt(input[0]) - 1;
            int y = Integer.parseInt(input[1]) - 1;
            dfs(x, y);
            int area = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (used[i][j]) {
                        ++area;
                    }
                }
            }
            writer.println(area);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
