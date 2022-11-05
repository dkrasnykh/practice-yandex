package training_2_0_B.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A_QuickSearchInArray {
    static int binsearch(int l, int r, int n, int[] a) {
        while (l < r) {
            int m = (r + l) / 2;
            if (a[m] >= n) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l == a.length-1 && a[l] != n) {
            return l + 1;
        }
        return l;
    }

    static int binsearch1(int l, int r, int n, int[] a) {
        while (l < r) {
            int m = (r + l) / 2;
            if (a[m] > n) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if(l==a.length-1 && n>=a[l]){
            return l+1;
        }
        return l;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String[] line = reader.readLine().split(" ");
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line[i]);
            }
            Arrays.sort(a);
            int k = Integer.parseInt(reader.readLine());
            List<Integer> ans = new ArrayList<>();
            int L, R;
            for (int i = 0; i < k; i++) {
                line = reader.readLine().split(" ");
                L = Integer.parseInt(line[0]);
                R = Integer.parseInt(line[1]);

                int iL = binsearch(0, n - 1, L, a);
                int iR = binsearch1(0, n - 1, R, a);

                ans.add(Math.max(iR - iL, 0));
            }
            writer.println(ans.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
