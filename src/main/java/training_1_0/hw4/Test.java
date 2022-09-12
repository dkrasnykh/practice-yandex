package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            int space1 = line.indexOf(' ');
            int space2 = line.lastIndexOf(' ');

            int keywordCount = Integer.parseInt(line.substring(0, space1));

            boolean caseInsensitive = line.substring(space1 + 1, space2).equals("no");
            boolean cannotStartWithDigit = line.substring(space2 + 1).equals("no");

            Set<String> keywords = new HashSet<>();
            for (int i = 0; i < keywordCount; i++) {
                String keyword = reader.readLine();
                if (caseInsensitive) {
                    keyword = keyword.toLowerCase(Locale.ROOT);
                }
                keywords.add(keyword);
            }

            Map<String, Integer> occurrences = new LinkedHashMap<>();
            int i;
            StringBuilder sequence = new StringBuilder();

            while ((i = reader.read()) != -1) {

                char ch = (char) i;

                boolean isTokenChar = Character.isLetterOrDigit(ch) || ch == '_';

                if (isTokenChar) {
                    sequence.append(ch);
                } else if (sequence.length() > 0) {
                    String token = sequence.toString();
                    sequence.delete(0, sequence.length());
                    if (cannotStartWithDigit && Character.isDigit(token.charAt(0))) {
                        continue;
                    }
                    if (token.chars().allMatch(c -> Character.isDigit((char) c))) {
                        continue;
                    }
                    if (caseInsensitive) {
                        token = token.toLowerCase(Locale.ROOT);
                    }
                    if (keywords.contains(token)) {
                        continue;
                    }
                    occurrences.compute(token, (k, v) -> v == null ? 1 : v + 1);
                }

            }

            int maxOccurrences = -1;
            String maxIdentifier = null;

            for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
                if (entry.getValue() > maxOccurrences) {
                    maxOccurrences = entry.getValue();
                    maxIdentifier = entry.getKey();
                }
            }

            System.out.println(maxIdentifier);
        }
    }
}
