package intensive.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class D {
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

    private static int[] findMaximumSubarray(int[] a, int low, int high) {
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

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
                int n = Integer.parseInt(reader.readLine());
                String[] priceStr = regex.split(reader.readLine());
                int[] price = new int[n];
                int[] pChange = new int[n];
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
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}


