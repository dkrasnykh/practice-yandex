package intensive.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            if (s.length() == 1) {
                System.out.println("");
                return;
            }
            boolean isReplaced = false;
            StringBuilder sb = new StringBuilder();
            if (s.charAt(0) != 'a') {
                sb.append('a').append(s.substring(1));
                isReplaced = true;
            } else {
                for (int i = 1; i < s.length() / 2; i++) {
                    if (s.charAt(i) != 'a') {
                        sb.append(s, 0, i).append('a').append(s.substring(i + 1));
                        isReplaced = true;
                        break;
                    }
                }
            }
            if (isReplaced) {
                System.out.println(sb);
            } else {
                sb.append(s, 0, s.length() - 1).append('b');
                System.out.println(sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
