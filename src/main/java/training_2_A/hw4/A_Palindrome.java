package training_2_A.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A_Palindrome {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] cnt = new int[26];
            String s = reader.readLine().trim();
            for (int i = 0; i < s.length(); ++i) {
                cnt[s.charAt(i) - 65] += 1;
            }
            StringBuilder sb = new StringBuilder();
            int middle = -1;
            for (int i = 0; i < 26; ++i) {
                for (int j = 0; j < cnt[i] / 2; ++j) {
                    sb.append((char) (i + 65));
                }
                if (middle == -1 && cnt[i] % 2 == 1) {
                    middle = i;
                }
            }
            StringBuilder sb2 = new StringBuilder(sb);
            sb.reverse();
            if (middle != -1) {
                sb2.append((char) (middle + 65));
            }
            sb2.append(sb);
            writer.println(sb2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
