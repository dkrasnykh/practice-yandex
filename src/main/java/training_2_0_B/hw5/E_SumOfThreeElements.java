package training_2_0_B.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class E_SumOfThreeElements {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int s = Integer.parseInt(reader.readLine().trim());
            String[] line = reader.readLine().trim().split(" ");
            int[] a = new int[Integer.parseInt(line[0])];
            for (int i = 1; i < line.length; i++) {
                a[i - 1] = Integer.parseInt(line[i]);
            }
            line = reader.readLine().trim().split(" ");
            int[] b = new int[Integer.parseInt(line[0])];
            for (int i = 1; i < line.length; i++) {
                b[i - 1] = Integer.parseInt(line[i]);
            }
            line = reader.readLine().trim().split(" ");
            Map<Integer, Integer> c = new HashMap<>();
            for (int i = 1; i < line.length; i++) {
                int e = Integer.parseInt(line[i]);
                if (!c.containsKey(e)) {
                    c.put(e, i - 1);
                }
            }
            int key;
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    key = s - (a[i] + b[j]);
                    if (c.containsKey(key)) {
                        writer.println(i + " " + j + " " + c.get(key));
                        return;
                    }
                }
            }
            writer.println("-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
