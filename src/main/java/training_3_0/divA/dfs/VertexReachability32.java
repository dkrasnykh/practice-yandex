package training_3_0.divA.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class VertexReachability32 {
    private static boolean[] used;
    private static List<Integer>[] adjList;
    private static void dfs(int v) {
        used[v] = true;
        for (Integer u : adjList[v]) {
            if (!used[u]) {
                dfs(u);
            }
        }
    }
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            used = new boolean[n + 1];
            adjList = new List[n + 1];
            for (int i = 1; i <= n; ++i) {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; ++i) {
                line = reader.readLine().split(" ");
                int a = Integer.parseInt(line[0]);
                int b = Integer.parseInt(line[1]);
                adjList[b].add(a);
            }
            dfs(1);
            for (int i = 1; i <= n; ++i) {
                if (used[i]) {
                    writer.print(i + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
