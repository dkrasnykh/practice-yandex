package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
//не прошло проверку на контесте
public class TaskD {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int maxlen = 256;
            int currlen = 0;
            char[] wordbuf = new char[maxlen];
            Set<String> words = new HashSet<>();

            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isWhitespace(c) && currlen > 0) {
                    String s = new String(wordbuf, 0, currlen);
                    words.add(s);
                    currlen = 0;
                } else {
                    if (currlen == maxlen) {
                        maxlen *= 1.5;
                        char[] xbuf = new char[maxlen];
                        System.arraycopy(wordbuf, 0, xbuf, 0, currlen);
                        wordbuf = xbuf;
                    }
                    wordbuf[currlen++] = (char) c;
                }
            }
            if (currlen > 0) {
                String s = new String(wordbuf, 0, currlen);
                words.add(s);
            }
            reader.close();

            System.out.println(words.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
