package training_1_0.hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskG {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long[] a = getLongArrayFromString(in.nextLine());
        long[] negative = getNegativeNumbers(a);
        long[] positive = getPositiveNumbers(a);
        long[] res1 = getMaxMultiply(a);
        long[] res2 = (negative.length > 1) ? getMaxMultiply(negative) : res1;
        long[] res3 = (positive.length > 1) ? getMaxMultiply(positive) : res1;
        if (res1[0] * res1[1] >= res2[0] * res2[1] && res1[0] * res1[1] >= res3[0] * res3[1]) {
            System.out.println(res1[0] + " " + res1[1]);
        } else if (res2[0] * res2[1] >= res1[0] * res1[1] && res2[0] * res2[1] >= res3[0] * res3[1]) {
            System.out.println(res2[0] + " " + res2[1]);
        } else {
            System.out.println(res3[0] + " " + res3[1]);
        }
    }

    static long[] getNegativeNumbers(long[] a) {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                list.add(a[i]);
            }
        }

        long[] ans = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    static long[] getPositiveNumbers(long[] a) {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0) {
                list.add(a[i]);
            }
        }
        long[] ans = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    static long[] getMaxMultiply(long[] a) {
        long n1 = a[0];
        long n2 = a[1];
        for (int i = 2; i < a.length; i++) {
            if (a[i] * n1 > n1 * n2 && a[i] * n1 >= a[i] * n2) {
                n2 = a[i];
            } else if (a[i] * n2 > n1 * n2 && a[i] * n2 >= a[i] * n1) {
                n1 = a[i];
            }
        }
        long output1;
        long output2;
        if (n1 <= n2) {
            output1 = n1;
            output2 = n2;
        } else {
            output1 = n2;
            output2 = n1;
        }
        return new long[]{output1, output2};
    }

    static long[] getLongArrayFromString(String s) {
        String[] a = s.split(" ");
        long[] ans = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            ans[i] = Long.parseLong(a[i]);
        }
        return ans;
    }
}