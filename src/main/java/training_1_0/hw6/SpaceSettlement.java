package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class SpaceSettlement {
    public static boolean check(long d, long a, long b, long w, long h, long n) {
        long k1 = w / (a + 2 * d);
        long k2 = h / (b + 2 * d);
        long k3 = w / (b + 2 * d);
        long k4 = h / (a + 2 * d);
        return (k1 * k2 >= n || k3 * k4 >= n);
    }
    public static long rbinsearch(long l, long r, long a, long b, long w, long h, long n) {
        while (l < r) {
            long m = (l + r + 1) / 2;
            if (check(m, a, b, w, h, n)) {
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
            Pattern regex = Pattern.compile(" ");
            String[] nabwh = regex.split(reader.readLine());
            long n = Long.parseLong(nabwh[0]);
            long a = Long.parseLong(nabwh[1]);
            long b = Long.parseLong(nabwh[2]);
            long w = Long.parseLong(nabwh[3]);
            long h = Long.parseLong(nabwh[4]);
            long d = rbinsearch(0 , Math.max(w, h), a, b, w, h, n);
            writer.println(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
