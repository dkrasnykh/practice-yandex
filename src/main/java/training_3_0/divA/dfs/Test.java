package training_3_0.divA.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Test {
    static int[] p1;
    static int[] p2;
    static int[] a;

    static boolean check(int m) {
        return p1[m] == p2[m];
    }

    static int rbinsearch(int l, int r) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (check(m)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    static int lbinsearch(int l, int r) {
        while (l < r) {
            int m = (l + r) / 2;
            if (check(m)) {
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
            p1 = new int[]{0, 0, 0, 1, 1, 0, 0};
            p2 = new int[]{0, 1, 0, 1, 0, 0, 1};
            a = new int[]{1, 2, 3, 4, 5, 6, 7};
            //writer.println();
            writer.println(lbinsearch(0, a.length - 1));
            writer.println(rbinsearch(0, a.length - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
