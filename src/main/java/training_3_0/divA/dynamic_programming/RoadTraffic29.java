package training_3_0.divA.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class RoadTraffic29 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int m = Integer.parseInt(line[0]);
            int n = Integer.parseInt(line[1]);
            long[][] dp = new long[n + 1][m + 1];
            Arrays.fill(dp[1], 1);
            for (int i = 2; i <= n; ++i) {
                dp[i][1] = 1;
            }
            for (int i = 2; i <= n; ++i) {
                for (int j = 2; j <= m; ++j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + dp[i - 1][j - 1];
                }
            }
            writer.println(dp[n][m]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
