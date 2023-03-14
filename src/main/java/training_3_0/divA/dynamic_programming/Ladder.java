package training_3_0.divA.dynamic_programming;

import java.io.*;

public class Ladder {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("ladder.in")));
             BufferedWriter writer = new BufferedWriter(new FileWriter("ladder.out"))) {
            String line = reader.readLine().trim();
            int n = Integer.parseInt(line);
            int[] a = new int[n + 1];
            String[] line1 = reader.readLine().split(" ");
            for (int i = 1; i <= n; ++i) {
                a[i] = Integer.parseInt(line1[i - 1]);
            }
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = a[1];
            for (int i = 2; i <= n; ++i) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2]) + a[i];
            }
            writer.write(String.valueOf(dp[n]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
