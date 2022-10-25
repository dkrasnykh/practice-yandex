package training_2_0_B.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class C_MakingPalindromes {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine().trim();
            if (line.length() <= 1) {
                writer.println(0);
                return;
            }
            int price = 0;
            for (int i = 0; i < line.length() / 2; i++) {
                if (line.charAt(i) != line.charAt(line.length() - 1 - i)) {
                    price++;
                }
            }
            writer.println(price);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
