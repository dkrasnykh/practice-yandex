package training_3_0.divA.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class KnightsMove26 {
    static BigInteger f(int i, int j, int n, int m, BigInteger[][] dp) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return BigInteger.ZERO;
        }
        if (dp[i][j].compareTo(BigInteger.valueOf(-1)) != 0) {
            return dp[i][j];
        }
        dp[i][j] = f(i - 2, j + 1, n, m, dp).add(f(i - 2, j - 1, n, m, dp)).add(f(i - 1, j - 2, n, m, dp)).add(f(i + 1, j - 2, n, m, dp));
        return dp[i][j];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            BigInteger[][] dp = new BigInteger[n][m];
            for (int i = 0; i < dp.length; ++i) {
                Arrays.fill(dp[i], BigInteger.valueOf(-1));
            }
            dp[0][0] = BigInteger.ONE;
            writer.println(f(n - 1, m - 1, n, m, dp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
