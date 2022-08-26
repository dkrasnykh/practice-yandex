package training_1_0.hw2;

import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int[] n = getIntArrayFromString(input);
        System.out.println((isSeqIncr(n)) ? "YES" : "NO");
    }
    static boolean isSeqIncr(int[] n) {
        if (n.length == 0) {
            return false;
        }
        if (n.length == 1) {
            return true;
        }
        boolean ans = true;
        for (int i = 1; i < n.length; i++) {
            if (n[i - 1] >= n[i]) {
                ans = false;
            }
        }
        return ans;
    }
    static int[] getIntArrayFromString(String s) {
        if (s.isEmpty() || s == null) {
            return new int[0];
        }
        String[] sa = s.split(" ");
        int[] ans = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            ans[i] = Integer.parseInt(sa[i]);
        }
        return ans;
    }
}
