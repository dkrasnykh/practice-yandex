package training_2_0_B.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class B_NumberOfLeftAndRightPosition {
    static int lbinsearch(int l, int r, int n, int[] a) {
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] >= n) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return (a[l] != n) ? 0 : l + 1;
    }
    static int rbinsearch(int l, int r, int n, int[] a) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (a[m] <= n) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return (a[l] != n) ? 0 : l + 1;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int[] a = new int[n];
            String[] line = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line[i]);
            }
            List<String> ans = new ArrayList<>();
            int m = Integer.parseInt(reader.readLine());
            line = reader.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                int c = Integer.parseInt(line[i]);
                ans.add(lbinsearch(0, n - 1, c, a) + " " + rbinsearch(0, n - 1, c, a));
            }
            for (String e : ans) {
                writer.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
