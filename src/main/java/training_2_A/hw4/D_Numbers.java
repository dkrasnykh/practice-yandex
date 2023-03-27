package training_2_A.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D_Numbers {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String a = reader.readLine().trim();
            String b = reader.readLine().trim();
            int[] acnt = new int[10];
            int[] bcnt = new int[10];
            for (int i = 0; i < a.length(); ++i) {
                acnt[a.charAt(i) - 48] += 1;
            }
            for (int i = 0; i < b.length(); ++i) {
                bcnt[b.charAt(i) - 48] += 1;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 9; i >= 0; --i) {
                int repeated = Math.min(acnt[i], bcnt[i]);
                sb.append(String.valueOf(i).repeat(Math.max(0, repeated)));
            }
            if (sb.isEmpty()) {
                writer.println(-1);
                return;
            }
            String ans = sb.toString();
            writer.println(ans.charAt(0) == '0' ? "0" : ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
