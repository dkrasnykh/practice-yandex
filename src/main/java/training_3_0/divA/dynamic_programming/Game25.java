package training_3_0.divA.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Game25 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int a = Integer.parseInt(line[1]);
            int b = Integer.parseInt(line[2]);
            int[] dp = new int[n + 1];
            dp[1] = 0;
            for (int i = 2; i <= n; ++i) {
                int value = Integer.MAX_VALUE;
                for (int j = 1; j <= i - 1; ++j) {
                    value = Math.min(Math.max(dp[j] + a, dp[i - j] + b), value);
                }
                dp[i] = value;
            }
            writer.println(dp[n]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
