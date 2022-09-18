package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class BinarySearch {
    static int lbinsearch(int l, int r, int param, int[] a) {
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
            Arrays.sort(n);
            for (int i = 0; i < kstr.length; i++) {
                int k = Integer.parseInt(kstr[i]);
                int j = lbinsearch(0, n.length - 1, k, n);
                writer.println((n[j] == k) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
