package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WordNumber {
    //https://contest.yandex.ru/contest/27665/problems/B/
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Map<String, Integer> words = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            StringBuilder ans = new StringBuilder();
            int lenght = 0;
            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isWhitespace(c) && lenght > 0) {
                    String word = sb.toString();
                    int count = (words.get(word) == null) ? 0 : words.get(word);
                    ans.append(count).append(" ");
                    words.put(word, ++count);
                    sb.delete(0, lenght);
                    lenght = 0;
                } else {
                    sb.append((char)c);
                    lenght++;
                }
            }
            reader.close();
            System.out.println(ans.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
