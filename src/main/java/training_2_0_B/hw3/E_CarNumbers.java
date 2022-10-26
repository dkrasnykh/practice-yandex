package training_2_0_B.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class E_CarNumbers {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int m = Integer.parseInt(reader.readLine());
            Set<Character>[] testimonies = new Set[m];
            String line;
            for (int i = 0; i < m; i++) {
                line = reader.readLine().trim();
                testimonies[i] = new HashSet<>();
                for (int j = 0; j < line.length(); j++) {
                    testimonies[i].add(line.charAt(j));
                }
            }
            int n = Integer.parseInt(reader.readLine().trim());
            Map<String, Integer> ans = new HashMap<>();
            int max = 0;
            Set<Character> temp;
            String[] a = new String[n];
            for (int i = 0; i < n; i++) {
                a[i] = reader.readLine().trim();
                temp = new HashSet<>();
                for (int j = 0; j < a[i].length(); j++) {
                    temp.add(a[i].charAt(j));
                }
                int cnt = 0;
                for (int j = 0; j < m; j++) {
                    if (temp.containsAll(testimonies[j])) {
                        cnt++;
                    }
                }
                if (!ans.containsKey(a[i])) {
                    ans.put(a[i], cnt);
                }
                if (max < cnt) {
                    max = cnt;
                }
            }
            for (int i = 0; i < a.length; i++) {
                if (ans.get(a[i]).equals(max)) {
                    writer.println(a[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
