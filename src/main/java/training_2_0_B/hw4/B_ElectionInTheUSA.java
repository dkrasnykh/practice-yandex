package training_2_0_B.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class B_ElectionInTheUSA {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            Map<String, Integer> map = new TreeMap<>();
            String input;
            while ((input = reader.readLine()) != null) {
                String[] data = input.trim().split(" ");
                int v = Integer.parseInt(data[1]);
                map.compute(data[0], (key, value) -> value == null ? v : value + v);
            }
            for(Map.Entry entry: map.entrySet()){
                writer.println(entry.getKey()+" "+entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
