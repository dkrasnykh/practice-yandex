package training_2_0_B.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D_BenchesInTheAtrium {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int L = Integer.parseInt(line[0]);
            int K = Integer.parseInt(line[1]);
            line = reader.readLine().trim().split(" ");
            int[] k = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                k[i] = Integer.parseInt(line[i]);
            }
            int l = 0;
            int r = k.length - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (k[m] >= L / 2) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            if (L % 2 != 0 && k[l] == L / 2) {
                writer.println(k[l]);
            } else {
                writer.println(k[l - 1] + " " + k[l]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
