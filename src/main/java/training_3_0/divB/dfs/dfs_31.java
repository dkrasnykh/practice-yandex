package training_3_0.divB.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class dfs_31 {
    private static List<Integer>[] adjList;
    private static boolean[] used;

    private static void dfs(int v, List<Integer> ans) {
        used[v] = true;
        ans.add(v);
        for (Integer u : adjList[v]) {
            if (!used[u]) {
                dfs(u, ans);
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
            used = new boolean[n + 1];
            Arrays.fill(used, false);
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
            List<Integer> ans = new ArrayList<>();
            dfs(1, ans);
            Collections.sort(ans);
            writer.println(ans.size());
            for (Integer v : ans) {
                writer.print(v+" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}