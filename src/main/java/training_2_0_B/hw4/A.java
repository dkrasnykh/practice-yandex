package training_2_0_B.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class A {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            Map<Long, Long> map = new TreeMap<>();
            String[] line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine().trim().split(" ");
                long k = Long.parseLong(line[0]);
                long v = Long.parseLong(line[1]);
                map.compute(k, (key, value) -> value == null ? v : value + v);
            }
            for (Map.Entry entry : map.entrySet()) {
                writer.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
