package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class ApproximateBinarySearch {
    static int searchLesser(int l, int r, int param, int[] a) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (a[m] <= param) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    static int searchLarger(int l, int r, int param, int[] a) {
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] >= param) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    static int closerNumber(int lesser, int larger, int k) {
        return (Math.abs(k - lesser) > Math.abs(k - larger)) ? larger : lesser;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            Pattern regex = Pattern.compile(" ");
            reader.readLine();
            String[] nstr = regex.split(reader.readLine());
            String[] kstr = regex.split(reader.readLine());
            int[] n = new int[nstr.length];
            for (int i = 0; i < nstr.length; i++) {
                n[i] = Integer.parseInt(nstr[i]);
            }
            for (int i = 0; i < kstr.length; i++) {
                int k = Integer.parseInt(kstr[i]);
                int l = searchLesser(0, n.length - 1, k, n);
                if (l == n.length - 1) {
                    writer.println(closerNumber(n[0], n[n.length - 1], k));
                } else {
                    int r = searchLarger(0, n.length-1, k, n);
                    writer.println(closerNumber(n[l], n[r], k));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
