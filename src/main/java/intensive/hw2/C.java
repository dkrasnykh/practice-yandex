package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class C {
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
            String[] adict = regex.split(reader.readLine());
            String[] text = regex.split(reader.readLine());
            //
            Map<Character, List<String>> dict = createMapDictionary(adict);

            for (int i = 0; i < text.length; i++) {
                char c = text[i].charAt(0);
                List<String> prefix = dict.get(c);
                boolean isPrinted = false;
                if (prefix != null) {
                    for (String p : prefix) {
                        if (text[i].startsWith(p)) {
                            System.out.print(p + " ");
                            isPrinted = true;
                            break;
                        }
                    }
                }
                if (!isPrinted) {
                    System.out.print(text[i] + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
