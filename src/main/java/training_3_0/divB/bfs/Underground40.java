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
import java.util.Set;
import java.util.HashSet;

public class Underground40 {
    private static List<Integer>[] adjList;
    private static Queue<Integer> q;
    private static int[] d;
    private static int INF = 10000;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            adjList = new List[m];
            d = new int[m];
            Arrays.fill(d, -1);
            Set<Integer>[] lines = new Set[m];
            for (int i = 0; i < m; ++i) {
                adjList[i] = new ArrayList<>();
                String[] str = reader.readLine().split(" ");
                Set<Integer> stations = new HashSet<>();
                for (int j = 1; j < str.length; ++j) {
                    int st = Integer.parseInt(str[j]);
                    stations.add(st);
                }
                lines[i] = stations;
            }
            String[] str = reader.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int f = Integer.parseInt(str[1]);
            Set<Integer> startLines = new HashSet<>();
            Set<Integer> endLines = new HashSet<>();
            for (int i = 0; i < m; ++i) {
                if (lines[i].contains(s)) {
                    startLines.add(i);
                }
                if (lines[i].contains(f)) {
                    endLines.add(i);
                }
                for (int j = i + 1; j < m; ++j) {
                    boolean contain = false;
                    for (Integer e : lines[i]) {
                        if (lines[j].contains(e)) {
                            contain = true;
                            break;
                        }
                    }
                    if (contain) {
                        adjList[i].add(j);
                        adjList[j].add(i);
                    }
                }
            }
            q = new ArrayDeque<>();
            for (Integer v : startLines) {
                q.offer(v);
                d[v] = 0;
            }
            while (!q.isEmpty()) {
                Integer v = q.poll();
                for (Integer u : adjList[v]) {
                    if (d[u] == -1) {
                        q.offer(u);
                        d[u] = d[v] + 1;
                    }
                }
            }
            int ans = INF;
            for(Integer v : endLines){
                ans = Math.min(ans, d[v]);
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
