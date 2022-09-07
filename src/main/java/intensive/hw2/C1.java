package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class C1 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            String[] dict = regex.split(reader.readLine());
            String[] text = regex.split(reader.readLine());
            Arrays.sort(dict);
            int[] posStart = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            int[] posEnd = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            char cur = dict[0].charAt(0);
            posStart[cur - 'a'] = 0;
            posEnd[cur - 'a'] = 0;
            for (int i = 1; i < dict.length; i++) {
                char c = dict[i].charAt(0);
                if (c != cur) {
                    posStart[c - 'a'] = i;
                    posEnd[cur - 'a'] = i;
                    cur = c;
                }
            }
            posEnd[cur - 'a'] = dict.length;
            for (int i = 0; i < text.length; i++) {
                char p = text[i].charAt(0);
                int k = posStart[p - 'a'];
                boolean isPrinted = false;
                if (k >= 0) {
                    for (int j = k; j < posEnd[p - 'a']; j++) {
                        if (text[i].startsWith(dict[j])) {
                            System.out.print(dict[j] + " ");
                            isPrinted = true;
                            break;
                        }
                    }
                }
                if (!isPrinted) {
                    System.out.print(text[i] + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
