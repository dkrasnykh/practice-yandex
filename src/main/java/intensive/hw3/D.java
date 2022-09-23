package intensive.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class D {
    static boolean check(int m, int k, int[] stalls) {
        int cowsCount = 1;
        int prevPos = stalls[0];
        for (int i = 0; i < stalls.length; i++) {
            if (stalls[i] - prevPos >= m) {
                cowsCount += 1;
                prevPos = stalls[i];
            }
        }
        return cowsCount >= k;
    }

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String nk = reader.readLine();
            int space = nk.indexOf(' ');
            int n = Integer.parseInt(nk.substring(0, space));
            int k = Integer.parseInt(nk.substring(space + 1));
            String[] stallsstr = regex.split(reader.readLine());
            int[] stalls = new int[n];
            for (int i = 0; i < n; i++) {
                stalls[i] = Integer.parseInt(stallsstr[i]);
            }
            int l = 0;
            int r = stalls[n - 1];
            while (r > l) {
                int m = (l + r + 1) / 2;
                if (check(m, k, stalls)) {
                    l = m;
                } else {
                    r = m - 1;
                }
            }
            writer.println(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
