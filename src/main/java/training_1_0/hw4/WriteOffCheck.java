package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

public class WriteOffCheck {
    public static void main(String[] args) throws IOException {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line1 = regex.split(reader.readLine());
            int n = Integer.parseInt(line1[0]);
            boolean caseSensitive = line1[1].equals("yes");
            boolean startsWithNumber = line1[2].equals("yes");
            Set<String> keyWords = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                keyWords.add(caseSensitive ? line : line.toLowerCase());
            }

            int c;
            StringBuilder sb = new StringBuilder();
            int lengh = 0;
            Map<String, Integer> map = new LinkedHashMap<>();
            while ((c = reader.read()) != -1) {

                boolean check = Character.isLetterOrDigit(c) || c == '_';

                if (!check && lengh > 0) {
                    String s = caseSensitive ? new String(sb) : new String(sb).toLowerCase();
                    sb.delete(0, lengh);
                    lengh = 0;
                    if (!keyWords.contains(s)) {
                        if (!startsWithNumber && Character.isDigit(s.charAt(0))) {
                            continue;
                        }
                        if(startsWithNumber && s.length()==1 && Character.isDigit(s.charAt(0))){
                            continue;
                        }
                        map.compute(s, (key, total) -> (total == null) ? 1 : total + 1);
                    }
                } else if (!check && lengh == 0) {
                    continue;
                } else {
                    lengh++;
                    sb.append((char) c);
                }
            }
            int maxvalue = map.values().stream().max(Comparator.comparingInt(v -> v)).get();
            String ans = map.entrySet().stream().filter((e -> e.getValue().equals(maxvalue))).findFirst().map(Map.Entry::getKey).get();
            writer.println(ans);
            writer.flush();
        }
    }
}
