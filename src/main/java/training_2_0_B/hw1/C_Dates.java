package training_2_0_B.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class C_Dates {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            writer.println(x >= 1 && x <= 12 && y >= 1 && y <= 12 && x != y ? 0 : 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
