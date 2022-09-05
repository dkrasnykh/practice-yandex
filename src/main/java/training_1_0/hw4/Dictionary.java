package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Dictionary {
    //https://contest.yandex.ru/contest/27665/problems/A/
    static Pattern REGEX = Pattern.compile(" ");

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(reader.readLine());

            Map<String, String> dictionary = new HashMap<>();

            String[] input;
            for (int i = 0; i < n; i++) {
                input = REGEX.split(reader.readLine());
                dictionary.put(input[0], input[1]);
                dictionary.put(input[1], input[0]);
            }
            System.out.println(dictionary.get(reader.readLine()));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
