package training_1_0.hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskH {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long[] a = getLongArrayFromString(in.nextLine());
        if (a.length < 2) {
            return;
        }
        long[] negative = getNegativeNumbers(a);
        if (a.length == negative.length) {
            long[] n3 = get3Max(a);
            System.out.println(n3[0] + " " + n3[1] + " " + n3[2]);
            return;
        }

        long[] positive = getPositiveNumbers(a);
        long[] res1 = getMaxMultiply(a);
        long[] res2 = (negative.length > 1) ? getMaxMultiply(negative) : res1;
        long[] res3 = (positive.length > 1) ? getMaxMultiply(positive) : res1;

        long[] withoutMax1Max2 = excludeMax1Max2(a, res1[0], res1[1]);
        long max31 = getThirdMaxMultiply(withoutMax1Max2, res1[0], res1[1]);

        withoutMax1Max2 = excludeMax1Max2(a, res2[0], res2[1]);
        long max32 = getThirdMaxMultiply(withoutMax1Max2, res2[0], res2[1]);

        withoutMax1Max2 = excludeMax1Max2(a, res3[0], res3[1]);
        long max33 = getThirdMaxMultiply(withoutMax1Max2, res3[0], res3[1]);

        if (res1[0] * res1[1] * max31 >= res2[0] * res2[1] * max32 && res1[0] * res1[1] * max31 >= res3[0] * res3[1] * max33) {
            System.out.println(res1[0] + " " + res1[1] + " " + max31);
        } else if (res2[0] * res2[1] * max32 >= res1[0] * res1[1] * max31 && res2[0] * res2[1] * max32 >= res3[0] * res3[1] * max33) {
            System.out.println(res2[0] + " " + res2[1] + " " + max32);
        } else {
            System.out.println(res3[0] + " " + res3[1] + " " + max33);
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
        return new long[]{n1, n2};
    }

    static long[] get3Max(long[] a) {
        long[] ans = new long[3];
        if (a.length == 3) {
            ans[0] = a[0];
            ans[1] = a[1];
            ans[2] = a[2];
            return ans;
        }
        long max1;
        long max2;
        long max3;
        if (a[0] >= a[1] && a[0] >= a[2]) {
            max1 = a[0];
            if (a[1] > a[2]) {
                max2 = a[1];
                max3 = a[2];
            } else {
                max2 = a[2];
                max3 = a[1];
            }
        } else if (a[1] >= a[0] && a[1] >= a[2]) {
            max1 = a[1];
            if (a[0] > a[2]) {
                max2 = a[0];
                max3 = a[2];
            } else {
                max2 = a[2];
                max3 = a[0];
            }
        } else {
            max1 = a[2];
            if (a[0] > a[1]) {
                max2 = a[0];
                max3 = a[1];
            } else {
                max2 = a[1];
                max3 = a[0];
            }
        }

        for (int i = 3; i < a.length; i++) {
            if (a[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = a[i];
            } else if (a[i] > max2) {
                max3 = max2;
                max2 = a[i];
            } else if (a[i] > max3) {
                max3 = a[i];
            }
        }
        ans[0] = max1;
        ans[1] = max2;
        ans[2] = max3;
        return ans;
    }

    static long getThirdMaxMultiply(long[] a, long max1, long max2) {
        if (a.length == 1) {
            return a[0];
        }
        long max3 = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max1 * max2 * max3 < max1 * max2 * a[i]) {
                max3 = a[i];
            }
        }
        return max3;
    }

    static long[] excludeMax1Max2(long[] a, long max1, long max2) {
        long[] b = new long[a.length - 2];
        int iMax1 = -1;
        int iMax2 = -1;
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (iMax1 == -1 && a[i] == max1) {
                iMax1 = i;
            } else if (iMax2 == -1 && a[i] == max2) {
                iMax2 = i;
            } else {
                b[j] = a[i];
                j++;
            }
        }
        return b;
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
