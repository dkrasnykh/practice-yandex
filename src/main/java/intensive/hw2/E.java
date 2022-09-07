package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class E {
    static String wordWithoutVowels(String w) {
        return w.replaceAll("a", "*")
                .replaceAll("e", "*")
                .replaceAll("i", "*")
                .replaceAll("o", "*")
                .replaceAll("u", "*");
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            String[] nword = regex.split(reader.readLine());
            int k = Integer.parseInt(reader.readLine());
            String[] kword = regex.split(reader.readLine());

            Set<String> firstQuality = new HashSet<>();
            Map<String, String> secondQuality = new HashMap<>();
            Map<String, String> thirdQuality = new HashMap<>();

            for (int i = 0; i < nword.length; i++) {
                firstQuality.add(nword[i]);
                if (!secondQuality.containsKey(nword[i].toLowerCase())) {
                    secondQuality.put(nword[i].toLowerCase(), nword[i]);
                }
                if (!thirdQuality.containsKey(wordWithoutVowels(nword[i].toLowerCase()))) {
                    thirdQuality.put(wordWithoutVowels(nword[i].toLowerCase()), nword[i]);
                }
            }
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < kword.length; i++) {
                if (firstQuality.contains(kword[i])) {
                    ans.add(kword[i]);
                } else if (secondQuality.containsKey(kword[i].toLowerCase())) {
                    ans.add(secondQuality.get(kword[i].toLowerCase()));
                } else if (thirdQuality.containsKey(wordWithoutVowels(kword[i].toLowerCase()))) {
                    ans.add(thirdQuality.get(wordWithoutVowels(kword[i].toLowerCase())));
                } else {
                    ans.add("");
                }
            }
            System.out.println(String.join(" ", ans));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
