package training_3_0.divB.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;

public class PathLength36 {
    private static List<Integer>[] adjList;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            adjList = new List[n + 1];
            for (int i = 1; i <= n; ++i) {
                adjList[i] = new ArrayList<>();
                String[] line = reader.readLine().split(" ");
                for (int j = 1; j <= n; ++j) {
                    if (line[j - 1].equals("1")) {
                        adjList[i].add(j);
                    }
                }
            }
            String[] line = reader.readLine().split(" ");
            int s = Integer.parseInt(line[0]);
            int f = Integer.parseInt(line[1]);
            Queue<Integer> q = new ArrayDeque<>();
            int[] d = new int[n + 1];
            int[] p = new int[n + 1];
            Arrays.fill(d, -1);
            Arrays.fill(p, -1);
            q.offer(s);
            d[s] = 0;
            p[s] = -1;
            while (!q.isEmpty()) {
                int v = q.poll();
                for (int i = 0; i < adjList[v].size(); ++i) {
                    int u = adjList[v].get(i);
                    if (d[u] == -1) {
                        d[u] = d[v] + 1;
                        p[u] = v;
                        q.offer(u);
                    }
                }
            }
            writer.println(d[f]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
