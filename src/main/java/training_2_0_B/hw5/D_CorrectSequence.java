package training_2_0_B.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D_CorrectSequence {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine().trim();
            int open = 0;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '(') {
                    open++;
                } else if (c == ')' && open > 0) {
                    open--;
                } else {
                    writer.println("NO");
                    return;
                }
            }
            if (open == 0) {
                writer.println("YES");
            } else {
                writer.println("NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
