package intensive.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class B {
    private static boolean check1(int m, int a, int[] age) {
        return age[m] > 0.5 * a + 7;
    }

    private static int lbinsearch(int l, int r, int a, int[] age) {
        while (l < r) {
            int m = (l + r) / 2;
            if (check1(m, a, age)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private static boolean check2(int m, int a, int[] age) {
        return age[m] <= a;
    }

    private static int rbinsearch(int l, int r, int a, int[] age) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (check2(m, a, age)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            String[] sage = regex.split(reader.readLine());
            int[] age = new int[sage.length];
            for (int i = 0; i < sage.length; i++) {
                age[i] = Integer.parseInt(sage[i]);
            }
            Arrays.sort(age);
            long count = 0;
            for (int i = 0; i < age.length; i++) {
                int l = lbinsearch(0, age.length - 1, age[i], age);
                int r = rbinsearch(0, age.length - 1, age[i], age);
                count += Math.max(r - l, 0);
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
