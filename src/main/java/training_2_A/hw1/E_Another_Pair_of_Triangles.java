package training_2_A.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class E_Another_Pair_of_Triangles {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int p = Integer.parseInt(reader.readLine().trim());
            int a = p / 3;
            int b = (p - a) / 2;
            int c = p - a - b;
            if (c >= (a + b)) {
                writer.println(-1);
                return;
            }
            writer.println(a + " " + b + " " + c);
            if (p % 2 == 0) {
                a = (p - 2) / 2;
                writer.println(2 + " " + a + " " + a);
            } else {
                a = (p - 1) / 2;
                writer.println(1 + " " + a + " " + a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
