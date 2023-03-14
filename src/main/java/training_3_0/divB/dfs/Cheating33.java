package training_3_0.divB.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cheating33 {
    private static List<Integer>[] adjList;
    private static int[] color;
    private static boolean isOk;
    private static void dfs(int v, int c) {
        color[v] = c;
        for (Integer u : adjList[v]) {
            if (color[u] == c) {
                isOk = false;
                return;
            } else if (color[u] == 0) {
                dfs(u, 3 - c);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            adjList = new List[n + 1];
            color = new int[n + 1];
            Arrays.fill(color, 0);
            for (int i = 1; i <= n; ++i) {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; ++i) {
                line = reader.readLine().split(" ");
                int a = Integer.parseInt(line[0]);
                int b = Integer.parseInt(line[1]);
                adjList[a].add(b);
                adjList[b].add(a);
            }
            isOk = true;
            for (int i = 1; i <= n; ++i) {
                if (color[i] == 0) {
                    dfs(i, 1);
                    if (!isOk) {
                        writer.println("NO");
                        return;
                    }
                }
            }
            writer.println("YES");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class CycleSearch35 {
        private static List<Integer>[] adjList;
        private static boolean[] used;



        public static void main(String[] args) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 PrintWriter writer = new PrintWriter(System.out)) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
