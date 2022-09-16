package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Robot {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int k = Integer.parseInt(reader.readLine());
            String s = reader.readLine();
            int r;
            long cnt = 0;
            for (int l = 0; l < s.length() - k; l++) {
                r = l + k;
                while (r < s.length() && s.charAt(r) == s.charAt(l + (r - l) % k)) {
                    cnt++;
                    r++;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
