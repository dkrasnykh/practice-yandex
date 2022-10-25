package training_2_0_B.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class A_NumberEqualToTheMaximum {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int max = 0;
            Map<Integer, Integer> map = new HashMap<>();
            while (true) {
                int n = Integer.parseInt(reader.readLine());
                if (n == 0) {
                    break;
                }
                if (max <= n) {
                    max = n;
                    map.compute(max, (key, value) -> (value == null) ? 1 : value + 1);
                }
            }
            writer.println(map.get(max));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
