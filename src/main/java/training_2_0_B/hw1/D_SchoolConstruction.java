package training_2_0_B.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class D_SchoolConstruction {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String[] line = reader.readLine().trim().split(" ");
            if (n == 0) {
                writer.println(0);
                return;
            }
            long[] a = new long[n];
            long[] pr1 = new long[n];
            long[] pr2 = new long[n];
            a[0] = Long.parseLong(line[0]);
            pr1[0] = 0;
            pr2[n - 1] = 0;
            for (int i = 1; i < n; i++) {
                a[i] = Long.parseLong(line[i]);
                pr1[i] = Math.abs(a[i] - a[i - 1]) * i + pr1[i - 1];
            }
            for (int i = n - 2; i >= 0; i--) {
                pr2[i] = Math.abs(a[i + 1] - a[i]) * (n - 1 - i) + pr2[i + 1];
            }
            long min = Long.MAX_VALUE;
            int imin = 0;
            for (int i = 0; i < a.length; i++) {
                if (min >= pr1[i] + pr2[i]) {
                    min = pr1[i] + pr2[i];
                    imin = i;
                }
            }
            writer.println(a[imin]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
