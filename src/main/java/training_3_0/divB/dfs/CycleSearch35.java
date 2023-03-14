package training_3_0.divB.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleSearch35 {
    private static List<Integer>[] adjList;
    private static int cycleStart;
    private static int cycleEnd;
    private static int[] color;
    private static Set<Integer> tmp;
    public static void dfs(int v, List<Integer> ans, int last) {
        color[v] = 1;
        for (Integer u : adjList[v]) {
            if (color[u] == 1) {
                if (u != last && cycleStart==-1) {
                    cycleStart = u;
                    cycleEnd = v;
                    for(int i = 1; i < color.length; ++i){
                        if(color[i]==1){
                            tmp.add(i);
                        }
                    }
                }
                continue;
            }
            if(color[u] == 0) {
                dfs(u, ans, v);
            }
        }
        if(tmp.contains(v)){
            ans.add(v);
        }
        color[v] = 2;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String value = reader.readLine();
            int n = Integer.parseInt(value);
            int m = 0;
            adjList = new List[n + 1];
            color = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                adjList[i] = new ArrayList<>();
                String[] line = reader.readLine().split(" ");
                for (int j = 1; j <= n; ++j) {
                    int v = Integer.parseInt(line[j - 1]);
                    if (v == 1) {
                        adjList[i].add(j);
                        ++m;
                    }
                }
            }
            if (m == 0) {
                writer.println("NO");
                return;
            }
            for (int i = 1; i <= n; i++) {
                if (color[i] == 0) {
                    List<Integer> ans = new ArrayList<>();
                    tmp = new HashSet<>();
                    cycleStart = -1;
                    dfs(i, ans, i);
                    if (cycleStart != -1) {
                        writer.println("YES");
                        List<Integer> cycleList = new ArrayList<>();
                        boolean print = false;
                        for (int j = 0; j <ans.size(); ++j) {
                            if (ans.get(j) == cycleEnd) {
                                print = true;
                            }
                            if (print) {
                                cycleList.add(ans.get(j));
                            }
                            if (ans.get(j) == cycleStart) {
                                break;
                            }
                        }
                        writer.println(cycleList.size());
                        for (Integer e : cycleList) {
                            writer.print(e + " ");
                        }
                        return;
                    }
                }
            }
            writer.println("NO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}