package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
/*
https://contest.yandex.ru/contest/27663/problems/I/
*/
public class Polyglots {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            Map<String, Integer> languages = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int m = Integer.parseInt(reader.readLine());
                Set<String> studentLanguages = new HashSet<>();
                for (int j = 0; j < m; j++) {
                    String language = reader.readLine();
                    if (!studentLanguages.contains(language)) {
                        studentLanguages.add(language);
                        languages.merge(language, 1, Integer::sum);
                    }
                }
            }
            List<String> everyoneKnows = languages.entrySet()
                    .stream()
                    .filter(e -> e.getValue().equals(n))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            System.out.println(everyoneKnows.size());
            everyoneKnows.forEach(System.out::println);

            System.out.println(languages.keySet().size());
            languages.keySet().forEach(System.out::println);
        }
    }
}
