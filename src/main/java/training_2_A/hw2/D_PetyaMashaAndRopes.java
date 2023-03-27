package training_2_A.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D_PetyaMashaAndRopes {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int total = 0;
            int maxlen = 0;
            String[] line = reader.readLine().trim().split(" ");
            for (int i = 0; i < n; ++i) {
                int d = Integer.parseInt(line[i]);
                total += d;
                maxlen = Math.max(d, maxlen);
            }
            int k = total - maxlen;
            if (k < maxlen) {
                writer.println(maxlen - k);
            } else {
                writer.println(total);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
