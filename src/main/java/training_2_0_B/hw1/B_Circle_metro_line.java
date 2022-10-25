package training_2_0_B.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class B_Circle_metro_line {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(line[0]);
            int i = Integer.parseInt(line[1]);
            int j = Integer.parseInt(line[2]);
            writer.println(Math.min(Math.abs(j - i) - 1, (i < j) ? n - j + i - 1 : n - i + j - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
