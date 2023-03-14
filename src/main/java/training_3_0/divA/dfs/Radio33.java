package training_3_0.divA.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Radio33 {
    private static int[][] color;
    private static List<Integer>[] adjList;
    private static int INF = 1000000;

    private static void dfs(int i, int v, int c) {
        color[i][v] = c;
        for (Integer u : adjList[v]) {
            if (color[i][u] == 0) {
                dfs(i, u, 3 - c);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[][] points = new int[n][2];
            for (int i = 0; i < n; ++i) {
                String[] line = reader.readLine().trim().split(" ");
                points[i][0] = Integer.parseInt(line[0]);
                points[i][1] = Integer.parseInt(line[1]);
            }
            adjList = new List[n];
            color = new int[n][n];
            for (int i = 0; i < n; ++i) {
                adjList[i] = new ArrayList<>();
                for (int j = 0; j < n; ++j) {
                    if (i != j) {
                        adjList[i].add(j);
                    }
                }
            }
            for (int i = 0; i < n; ++i) {
                int v = i;
                adjList[i].sort(Comparator.comparingInt(a -> ((points[v][0] - points[a][0]) * (points[v][0] - points[a][0]) + (points[v][1] - points[a][1]) * (points[v][1] - points[a][1]))));
            }
            int maxDist = 0;
            int kmax = 0;
            for (int k = 0; k < n; ++k) {
                Arrays.fill(color[k], 0);
                dfs(k, k, 1);
                int minDist = INF;
                for (int i = 0; i < n; ++i) {
                    if (color[k][i] == 1) {
                        for (int j = i + 1; j < n; ++j) {
                            if (color[k][j] == 1) {
                                minDist = Math.min(minDist, (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]));
                            }
                        }
                    }
                    if (color[k][i] == 2) {
                        for (int j = i + 1; j < n; ++j) {
                            if (color[k][j] == 2) {
                                minDist = Math.min(minDist, (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]));
                            }
                        }
                    }
                }
                if (maxDist < minDist) {
                    maxDist = minDist;
                    kmax = k;
                }
            }
            BigDecimal bd = new BigDecimal(maxDist);
            writer.println(bd.sqrt(MathContext.DECIMAL128).divide(BigDecimal.valueOf(2)).setScale(15, RoundingMode.HALF_EVEN));
            for (int i = 0; i < n; ++i) {
                writer.print(color[kmax][i] + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
