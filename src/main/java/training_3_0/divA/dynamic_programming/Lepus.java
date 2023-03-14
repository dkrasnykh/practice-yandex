package training_3_0.divA.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Lepus {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String line = reader.readLine();
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                a[i] = (line.charAt(i - 1) == 'W') ? -1 : (line.charAt(i - 1) == '.' ? 0 : 1);
            }
            //k = 1, 3, 5
            long[] dp = new long[n + 1];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            dp[1] = 0;
            if (a[2] != -1) {
                dp[2] = a[2];
            }
            if (n <= 2) {
                writer.println(dp[n]);
                return;
            }
            if(a[3] != -1){
                dp[3] = Math.max(dp[2], dp[0]) + a[3];
            }
            if(a[4]!=-1){
                dp[4] = Math.max(dp[1], dp[3]) + a[4];
            }
            for (int i = 5; i <= n; ++i) {
                //dp[i] = (a[i] == -1) ?  : Math.max(Math.max(dp[i - 1], dp[i - 2]), dp[i - 3]) + a[i];
                if (a[i] != -1) {
                    dp[i] = Math.max(Math.max(dp[i - 1], dp[i - 3]), dp[i - 5]) + a[i];
                }
            }
            writer.println(dp[n]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
