package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Substring {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine();
            int space = line.indexOf(' ');
            int k = Integer.parseInt(line.substring(space + 1));
            String s = reader.readLine();
            int max = 0;
            int imax = 0;
            int r = 0;
            int[] count = new int[26];
            for (int l = 0; l < s.length(); l++) {
                while (r < s.length() && count[s.charAt(r) - 'a'] < k) {
                    count[s.charAt(r) - 'a']++;
                    r++;
                }
                if (r - l > max) {
                    max = r - l;
                    imax = l;
                }
                count[s.charAt(l) - 'a']--;
            }
            writer.println(max + " " + (imax + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
