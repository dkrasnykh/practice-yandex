package training_3_0.divB.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopologicalSort34 {
    private static List<Integer>[] adjList;
    private static int[] color;
    private static int[] ans;
    private static int pos;

    private static boolean dfs(int v) {
        color[v] = 1;
        for (Integer u : adjList[v]) {
            if (color[u] == 0) {
                if (dfs(u)) {
                    return true;
                }
            } else if (color[u] == 1) {
                return true;
            }
        }
        color[v] = 2;
        ans[pos] = v;
        --pos;
        return false;
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
            ans = new int[n + 1];
            pos = n;
            for (int i = 1; i <= n; ++i) {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; ++i) {
                line = reader.readLine().split(" ");
                int a = Integer.parseInt(line[0]);
                int b = Integer.parseInt(line[1]);
                adjList[a].add(b);
            }
            for (int i = 1; i <= n; ++i) {
                if (color[i] == 0) {
                    if (dfs(i)) {
                        writer.println(-1);
                        return;
                    }
                }
            }
            for (int i = 1; i <= n; ++i) {
                writer.print(ans[i] + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
