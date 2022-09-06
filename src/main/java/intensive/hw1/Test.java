package intensive.hw1;

import java.io.IOException;
import java.util.Random;

public class Test {

    static String generateString() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(r.nextInt(100)+1).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String s = generateString();
            //81 18 84 44 11 75
            //String s ="81 18 84 44 11 75";
            System.out.println(s);
            testing1(s);
            testing2(s);
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    }

    static void testing1(String s){
        String[] priceStr = s.split(" ");
        int[] price = new int[6];

        for (int i = 0; i < priceStr.length; i++) {
            price[i] = Integer.parseInt(priceStr[i]);
        }
        int i1 = 0;
        int i2 = 0;
        int maxSum = 0;
        for (int i = 0; i < price.length - 1; i++) {
            for (int j = i + 1; j < price.length; j++) {
                int k = 1000 / price[i];
                int k2 = 1000 % price[i];
                int p = price[j] * k + k2 - 1000;
                if (p > maxSum) {
                    i1 = i;
                    i2 = j;
                    maxSum = p;
                }
            }
        }
        if (maxSum > 0) {
            System.out.println((i1 + 1) + " " + (i2 + 1));
        } else {
            System.out.println("0 0");
        }
    }

    static void testing2(String s){
        String[] priceStr = s.split(" ");
        int[] price = new int[6];
        int[] pChange = new int[6];
        //int[] priceChange = new int[n];
        price[0] = Integer.parseInt(priceStr[0]);
        pChange[0] = 0;
        //priceChange[0] = 0;
        for (int i = 1; i < priceStr.length; i++) {
            price[i] = Integer.parseInt(priceStr[i]);
            pChange[i] = price[i] - price[i - 1];
        }
        //int[] ans = findMaximumSubarray(pChange, 0, pChange.length - 1);
        int[] ans = findMaximumSubarray(pChange, 0, pChange.length - 1);
        if (ans[2] <= 0) {
            System.out.println("0 0");
        } else {
            System.out.println(ans[0] + " " + (ans[1] + 1));
        }
    }

    static int[] findMaxCrossingSubarray(int[] a, int low, int mid, int high) {
        int left_sum = Integer.MIN_VALUE;
        int sum = 0;
        int max_left = 0;
        for (int i = mid; i >= low; i--) {
            sum += a[i];
            if (sum > left_sum) {
                left_sum = sum;
                max_left = i;
            }
        }
        int right_sum = Integer.MIN_VALUE;
        sum = 0;
        int max_right = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum += a[j];
            if (sum > right_sum) {
                right_sum = sum;
                max_right = j;
            }
        }
        return new int[]{max_left, max_right, left_sum + right_sum};
    }

    static int[] findMaximumSubarray(int[] a, int low, int high) {
        if (high == low) {
            return new int[]{low, high, a[low]};
        } else {
            int mid = (low + high) / 2;
            int[] left = findMaximumSubarray(a, low, mid);
            int[] right = findMaximumSubarray(a, mid + 1, high);
            int[] cross = findMaxCrossingSubarray(a, low, mid, high);
            int left_sum = left[2];
            int right_sum = right[2];
            int cross_sum = cross[2];
            if (left_sum > right_sum && left_sum > cross_sum) {
                return left;
            } else if (right_sum > left_sum && right_sum > cross_sum) {
                return right;
            } else {
                return cross;
            }
        }
    }
}
