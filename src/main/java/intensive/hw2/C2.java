package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class C2 {
    static Map<Character, List<String>> createMapDictionary(String[] adict) {
        Arrays.sort(adict);
        Map<Character, List<String>> dict = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(adict[0]);
        char c = adict[0].charAt(0);
        for (int i = 1; i < adict.length; i++) {
            char cur = adict[i].charAt(0);
            if (cur != c) {
                dict.put(c, values);
                c = cur;
                values = new ArrayList<>();
                values.add(adict[i]);
            } else {
                values.add(adict[i]);
            }
        }
        dict.put(c, values);
        return dict;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            String[] dict = regex.split(reader.readLine());
            String[] text = regex.split(reader.readLine());
            String[] text1 = text.clone();
            Map<Character, List<String>> textDict = createMapDictionary(text1);
            Map<String, String> wordPrefix = new HashMap<>();
            Arrays.sort(dict);

            for (int i = 0; i < dict.length; i++) {
                char c = dict[i].charAt(0);
                List<String> words = textDict.get(c);
                if (words != null) {
                    for (String w : words) {
                        if (w.startsWith(dict[i])) {
                            int finalI = i;
                            wordPrefix.computeIfAbsent(w, k -> dict[finalI]);
                        }
                    }
                }
            }

            for (int i = 0; i < text.length; i++) {
                String prefix = wordPrefix.get(text[i]);
                if (prefix != null) {
                    System.out.print(prefix + " ");
                } else {
                    System.out.print(text[i] + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
