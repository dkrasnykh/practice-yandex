package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Maya1 {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] input = regex.split(reader.readLine());
            int gl = Integer.parseInt(input[0]);
            int sl = Integer.parseInt(input[1]);
            String g = reader.readLine();
            String s = reader.readLine();
            char[] chg = g.toCharArray();
            char[] chs = s.toCharArray();
            int[] gLowerCase = new int[26];
            int[] gUpperCase = new int[26];
            for (int i = 0; i < gl; i++) {
                if (chg[i] > 96) {
                    gLowerCase[chg[i] - 'a']++;
                } else {
                    gUpperCase[chg[i] - 'A']++;
                }
            }
            int cnt = 0;
            int[] sLowerCase;
            int[] sUpperCase;
            char[] chForEquals = new char[gl];
            for (int i = 0; i < s.length() - g.length() + 1; i++) {
                sLowerCase = new int[26];
                sUpperCase = new int[26];
                System.arraycopy(chs, i, chForEquals, 0, gl);
                for (int j = 0; j < gl; j++) {
                    if (chForEquals[j] > 96) {
                        sLowerCase[chForEquals[j] - 'a']++;
                    } else {
                        sUpperCase[chForEquals[j] - 'A']++;
                    }
                }
                if (Arrays.equals(gLowerCase, sLowerCase) && (Arrays.equals(gUpperCase, sUpperCase))) {
                    cnt++;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
