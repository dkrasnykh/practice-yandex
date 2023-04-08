package training_2_A.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D_Parentheses {
    private static long sumOfProgression(int n) {
        return (long) (1 + n) * n / 2;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String seq = reader.readLine().trim();
            int n = seq.length();
            long res = sumOfProgression(n + 1);
            int balance = 0;
            int lenght = 0;
            for (int i = 0; i < seq.length(); ++i) {
                if (seq.charAt(i) == '(') {
                    balance += 1;
                } else {
                    balance -= 1;
                }
                if (balance != 0) {
                    lenght += 1;
                } else {
                    res += sumOfProgression(lenght);
                    lenght = 0;
                }
            }
            writer.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
