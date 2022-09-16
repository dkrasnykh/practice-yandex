package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Robot1 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int k = Integer.parseInt(reader.readLine());
            String s = reader.readLine();
            long p = 0;
            long cnt = 0;
            for (int i = k; i < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i - k)) {
                    p++;
                    if (i == s.length() - 1) {
                        cnt += p * (1 + p) / 2;
                    }
                } else if (p > 0) {
                    cnt += p * (1 + p) / 2;
                    p = 0;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
