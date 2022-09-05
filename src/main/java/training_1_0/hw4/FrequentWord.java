package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FrequentWord {
    //https://contest.yandex.ru/contest/27665/problems/C/
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int c;
            int lenght = 0;
            Map<String, Integer> words = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            while ((c = reader.read()) != -1) {
                if (Character.isWhitespace(c) && lenght > 0) {
                    String word = sb.toString();
                    words.merge(word, 1, Integer::sum);
                    sb.delete(0, lenght);
                    lenght = 0;
                } else {
                    sb.append((char) c);
                    lenght++;
                }
            }
            reader.close();

            int maxCount = Collections.max(words.values());
            String ans = words.entrySet()
                    .stream()
                    .filter(e -> e.getValue().equals(maxCount))
                    .map(Map.Entry::getKey)
                    .sorted()
                    .findFirst()
                    .get();
            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
