package training_2_A.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class B_Puzzle {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[] rebus = new int[26];
            int[] word = new int[26];
            for (int i = 0; i < n; ++i) {
                String s = reader.readLine().trim();
                for (int j = 0; j < s.length(); ++j) {
                    rebus[s.charAt(j) - 65] += 1;
                }
            }
            for (int i = 0; i < m; ++i) {
                String s = reader.readLine().trim();
                for (int j = 0; j < s.length(); ++j) {
                    word[s.charAt(j) - 65] += 1;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; ++i) {
                sb.append(String.valueOf((char) (i + 65)).repeat(Math.max(0, (rebus[i] - word[i]))));
            }
            writer.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
