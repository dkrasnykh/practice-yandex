package training_2_0_B.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A_PrefixSums {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(line[0]);
            int q1 = Integer.parseInt(line[1]);
            line = reader.readLine().trim().split(" ");
            long[] a = new long[n];
            long[] prefix = new long[n + 1];
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(line[i]);
                prefix[i + 1] = a[i] + prefix[i];
            }
            long[] ans = new long[q1];
            int l, r;
            for (int i = 0; i < q1; i++) {
                line = reader.readLine().trim().split(" ");
                l = Integer.parseInt(line[0]) - 1;
                r = Integer.parseInt(line[1]);
                ans[i] = prefix[r] - prefix[l];
            }
            for (int i = 0; i < ans.length; i++) {
                writer.println(ans[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
