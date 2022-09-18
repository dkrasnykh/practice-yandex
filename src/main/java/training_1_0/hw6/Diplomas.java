package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Diplomas {
    static boolean check(int m, List<Long> a, int w, int h, int n) {
        long k1 = a.get(m) / w;
        long k2 = a.get(m) / h;
        return n <= k1 * k2;
    }

    static int lbinsearch(int l, int r, List<Long> a, int w, int h, int n) {
        while (l < r) {
            int m = (l + r) / 2;
            if (check(m, a, w, h, n)) {
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
            String[] whn = regex.split(reader.readLine());
            int w = Integer.parseInt(whn[0]);
            int h = Integer.parseInt(whn[1]);
            int n = Integer.parseInt(whn[2]);
            int min = Math.min(w, h);
            int max = Math.max(w, h);
            int nl = (int) Math.sqrt(n);
            List<Long> val = new ArrayList<>();
            int i = 1;
            long last = 0;
            if (min == max) {
                while (last <= (long) nl * max) {
                    val.add(i * ((long) min));
                    last = i * ((long) min);
                    i++;
                }
            } else {
                while (last <= (long) nl * max) {
                    val.add(i * ((long) min));
                    val.add(i * ((long) max));
                    last = i * ((long) min);
                    i++;
                }
            }
            Collections.sort(val);
            int j = lbinsearch(0, val.size() - 1, val, w, h, n);
            writer.println(val.get(j));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
