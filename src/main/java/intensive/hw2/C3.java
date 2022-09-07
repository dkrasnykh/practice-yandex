package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class C3 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            String[] adict = regex.split(reader.readLine());
            String[] text = regex.split(reader.readLine());
            Set<String> dict = new HashSet<>(Arrays.asList(adict));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < text.length; i++) {
                boolean added = false;
                for (int j = 1; j <= text[i].length(); j++) {
                    String prefix = text[i].substring(0, j);
                    if (dict.contains(prefix)) {
                        sb.append(prefix).append(" ");
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    sb.append(text[i]).append(" ");
                }
            }
            System.out.println(sb.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
