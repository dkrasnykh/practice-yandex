package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Wires {
    static boolean check(int m, int k, int[] lk) {
        int count = 0;
        for (int i = 0; i < lk.length; i++) {
            count += lk[i] / m;
        }
        return count >= k;
    }
    static int rbinsearch(int l, int r, int k, int[] lk) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (check(m, k, lk)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine();
            int space = line.indexOf(' ');
            int n = Integer.parseInt(line.substring(0, space));
            int k = Integer.parseInt(line.substring(space + 1));
            int[] lk = new int[n];
            lk[0] = Integer.parseInt(reader.readLine());
            int max = lk[0];
            for (int i = 1; i < n; i++) {
                lk[i] = Integer.parseInt(reader.readLine());
                if (lk[i] > max) {
                    max = lk[i];
                }
            }
            int ans = rbinsearch(0, max, k, lk);
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
