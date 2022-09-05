package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Heap {
    //https://contest.yandex.ru/contest/27665/problems/E/
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(reader.readLine());
            Map<Integer, Integer> heap = new HashMap<>();
            String[] input;
            for (int i = 0; i < n; i++) {
                input = regex.split(reader.readLine());
                int wi = Integer.parseInt(input[0]);
                int hi = Integer.parseInt(input[1]);
                int h0 = heap.getOrDefault(wi, 0);
                if (h0 < hi) {
                    heap.put(wi, hi);
                }
            }
            long h = 0;
            for (Integer v : heap.values()) {
                h += v;
            }
            System.out.println(h);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
