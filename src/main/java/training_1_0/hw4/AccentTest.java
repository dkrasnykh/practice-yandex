package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.HashMap;

public class AccentTest {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            Map<String, Set<String>> mapdict = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String w = reader.readLine();
                String key = w.toLowerCase();
                if (mapdict.containsKey(key)) {
                    mapdict.get(key).add(w);
                } else {
                    mapdict.put(key, new HashSet<>(Collections.singletonList(w)));
                }
            }
            int cnt = 0;
            int c;
            int wlengh = 0;
            int upperCaseCounter = 0;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.read()) != -1) {
                if (Character.isWhitespace(c) && wlengh > 0) {
                    String s1 = new String(sb);
                    String key = s1.toLowerCase();
                    if(!mapdict.containsKey(key) && upperCaseCounter!=1 || mapdict.containsKey(key) && !(mapdict.get(key).contains(s1))){
                        cnt++;
                    }
                    sb.delete(0, wlengh);
                    wlengh = 0;
                    upperCaseCounter = 0;
                    if (c == 10) break;
                } else {
                    if (Character.isUpperCase(c)) {
                        upperCaseCounter++;
                    }
                    sb.append((char)c);
                    wlengh++;
                }
            }
            writer.println(cnt);
            writer.flush();
        }
    }
}
