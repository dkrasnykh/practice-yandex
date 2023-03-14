package training_3_0.divA.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Sawmill30 {
    private static long[][] dp;
    private static int[] cut;
    private static long f(int l, int r) {
        if (l + 1 == r) {
            return 0;
        }
        long value = Long.MAX_VALUE;
        for (int i = l + 1; i < r; ++i) {
            if (dp[l][i] == -1) {
                dp[l][i] = f(l, i);
            }
            if (dp[i][r] == -1) {
                dp[i][r] = f(i, r);
            }
            value = Math.min(value, dp[l][i] + dp[i][r]);
        }
        dp[l][r] = cut[r] - cut[l] + value;
        return dp[l][r];
    }

    public static void main(String[] args) {
        //What will the following code print?
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int l = Integer.parseInt(line[0]);
            int n = Integer.parseInt(line[1]);
            cut = new int[n + 2];
            cut[0] = 0;
            cut[n + 1] = l;
            line = reader.readLine().split(" ");
            for (int i = 1; i <= n; ++i) {
                cut[i] = Integer.parseInt(line[i - 1]);
            }
            dp = new long[n + 2][n + 2];
            for (int i = 0; i < dp.length; ++i) {
                Arrays.fill(dp[i], -1);
            }
            writer.println(f(0, n + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
