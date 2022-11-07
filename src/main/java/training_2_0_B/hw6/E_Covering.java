package training_2_0_B.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class E_Covering {
    static int lbinsearch2(int l, int r, long k, long[] x) {
        if (k >= x[x.length - 1]) {
            return -1;
        }
        while (l < r) {
            int m = (l + r) / 2;
            if (x[m] > k) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    static boolean check(long l, int k, long[] x) {
        int count = 0;
        int i = 0;
        while (i != -1) {
            count++;
            i = lbinsearch2(0, x.length - 1, x[i] + l, x);
        }
        return count <= k;
    }

    static long lbinsearch1(long l, long r, int k, long[] x) {
        while (l < r) {
            long m = (l + r) / 2;
            if (check(m, k, x)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int k = Integer.parseInt(line[1]);
            long[] x = new long[n];
            line = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                x[i] = Long.parseLong(line[i]);
            }
            Arrays.sort(x);
            long r = (x[0] < 0) ? -x[0] + x[n - 1] : x[n - 1];

            writer.println(lbinsearch1(0, r, k, x));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
