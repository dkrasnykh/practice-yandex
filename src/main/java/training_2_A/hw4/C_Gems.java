package training_2_A.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C_Gems {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(line[0]);
            int k = Integer.parseInt(line[1]);
            String gems = reader.readLine().trim();
            Map<Character, List<Character>> pair = new HashMap<>();
            for (int i = 0; i < k; ++i) {
                String s = reader.readLine().trim();
                if (pair.containsKey(s.charAt(1))) {
                    pair.get(s.charAt(1)).add(s.charAt(0));
                } else {
                    List<Character> e = new ArrayList<>(List.of(s.charAt(0)));
                    pair.put(s.charAt(1), e);
                }
            }
            Map<Character, Integer> cnt = new HashMap<>();
            long ans = 0;
            for (int i = 0; i < gems.length(); ++i) {
                if (pair.containsKey(gems.charAt(i))) {
                    for (Character c : pair.get(gems.charAt(i))) {
                        ans += (long)(cnt.getOrDefault(c, 0));
                    }
                }
                cnt.compute(gems.charAt(i), (key, value) -> (value == null) ? 1 : value + 1);
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
