package training_3_0.divA.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpaceGarbageCollector28 {
    private static void populate(Map<Character, Integer> dct) {
        dct.put('N', 0);
        dct.put('S', 1);
        dct.put('W', 2);
        dct.put('E', 3);
        dct.put('U', 4);
        dct.put('D', 5);
    }
    private static long compute(int c, int n, long[][] dp, List<Integer>[] rules) {
        long res = 0;
        for (int i = 0; i < rules[c].size(); ++i) {
            res += dp[n - 1][rules[c].get(i)];
        }
        return res;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            Map<Character, Integer> dct = new HashMap<>();
            populate(dct);
            List<Integer>[] rules = new List[6];
            for (int i = 0; i < 6; ++i) {
                String line = reader.readLine().trim();
                rules[i] = new ArrayList<>();
                for (int j = 0; j < line.length(); ++j) {
                    rules[i].add(dct.get(line.charAt(j)));
                }
            }
            String[] line1 = reader.readLine().split(" ");
            int command = dct.get(line1[0].charAt(0));
            int n = Integer.parseInt(line1[1]);
            long[][] dp = new long[n + 1][6];
            for (int i = 0; i < 6; ++i) {
                dp[1][i] = rules[i].size();
                dp[2][i] = 1 + dp[1][i];
            }
            for (int i = 3; i <= n; ++i) {
                for (int j = 0; j < 6; ++j) {
                    dp[i][j] = 1 + compute(j, i, dp, rules);
                }
            }
            writer.print(dp[n][command]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
