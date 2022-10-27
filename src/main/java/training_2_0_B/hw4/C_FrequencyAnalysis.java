package training_2_0_B.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C_FrequencyAnalysis {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            Map<String, Integer> dict = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isWhitespace(c) && sb.length() > 0) {
                    String word = new String(sb);
                    dict.compute(word, (key, value) -> value == null ? 1 : value + 1);
                    sb.delete(0, word.length());
                } else {
                    sb.append((char) c);
                }
            }
            List<Object[]> ans = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : dict.entrySet()) {
                ans.add(new Object[]{entry.getValue(), entry.getKey()});
            }
            ans.sort((o1, o2) -> o1[0].equals(o2[0]) ? ((String) o1[1]).compareTo((String) o2[1]) : (int) o2[0] - (int) o1[0]);
            for (Object[] e : ans) {
                writer.println(e[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
