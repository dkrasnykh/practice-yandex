package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class TaskD1 {
    public static void main(String[] args) throws IOException {

        Set<String> set = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.read()) != -1) {
                if (Character.isWhitespace(c) && sb.length()>0) {
                    String word = sb.toString();
                    set.add(word);
                    sb.delete(0, sb.length());
                }
                else {
                    sb.append(c);
                }
            }
        }
        System.out.println(set.size());
    }
}
