package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Square {
    static boolean check(int k, int n, int m, long c) {
        if (k > Math.min(n, m) / 2) {
            return false;
        }
        long sn = ((2 * (long) n - 2 * (long) (k - 1)) * k) / 2;
        long sm = ((2 * (long) m - 2 * (long) (k - 1)) * k) / 2;
        return (sn + sm - 2 * (long) k)*2 <= c;
    }

    static int rbinsearch(int l, int r, int n, int m, long c) {
        while (l < r) {
            int k = (l + r + 1) / 2;
            if (check(k, n, m, c)) {
                l = k;
            } else {
                r = k - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            long l = Long.parseLong(reader.readLine());
            int k = rbinsearch(0, Math.min(n, m) / 2, n, m, l);
            writer.println(k);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
