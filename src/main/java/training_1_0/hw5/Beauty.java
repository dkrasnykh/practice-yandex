package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Beauty {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] nk = regex.split(reader.readLine());
            int k = Integer.parseInt(nk[1]);
            String[] ts = regex.split(reader.readLine());
            int[] t = new int[ts.length];
            for (int i = 0; i < ts.length; i++) {
                t[i] = Integer.parseInt(ts[i]);
            }
            int last = 0;
            Map<Integer, Integer> map = new HashMap<>();
            while (last < t.length && map.size() < k) {
                map.compute(t[last], (key, total) -> total == null ? 1 : total + 1);
                last++;
            }
            int firstmin = 0;
            int lastmin = last - 1;
            int min = lastmin - firstmin;
            for (int i = 1; i < t.length; i++) {
                int c = map.get(t[i - 1]);
                if (c > 1) {
                    map.put(t[i - 1], c - 1);
                } else {
                    map.remove(t[i - 1], c);
                }
                while (last < t.length && map.size() < k) {
                    map.compute(t[last], (key, total) -> total == null ? 1 : total + 1);
                    last++;
                }
                if (map.size() >= k && last - 1 - i < min) {
                    firstmin = i;
                    lastmin = last - 1;
                    min = lastmin - firstmin;
                }
            }
            writer.println((firstmin + 1) + " " + (lastmin + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
