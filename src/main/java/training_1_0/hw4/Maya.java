package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Maya {
    //https://contest.yandex.ru/contest/27665/problems/H/
    //TL test 13
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] input = regex.split(reader.readLine());
            int gl = Integer.parseInt(input[0]);
            int sl = Integer.parseInt(input[1]);
            String g = reader.readLine();
            String s = reader.readLine();
            char[] chars = g.toCharArray();
            Arrays.sort(chars);
            g = new String(chars);
            int cnt = 0;
            for (int i = 0; i < s.length() - g.length() + 1; i++) {
                s.getChars(i, i + gl, chars, 0);
                Arrays.sort(chars);
                String seq = new String(chars);
                if (g.equals(seq)) {
                    cnt++;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
