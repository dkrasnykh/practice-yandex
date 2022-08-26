package training_1_0.hw2;

import java.util.Scanner;

public class TaskC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        short n = Short.parseShort(in.nextLine());
        String s = in.nextLine();
        short[] a = getShortArrayFromString(s, n);
        short x = in.nextShort();
        System.out.println(getAccurateValue(a, x));
    }
    static short[] getShortArrayFromString(String s, int n) {
        short[] ans = new short[n];
        if (n == 1) {
            ans[0] = Short.parseShort(s);
            return ans;
        }
        String[] a = s.split(" ");
        for (int i = 0; i < n; i++) {
            ans[i] = Short.parseShort(a[i]);
        }
        return ans;
    }
    static short getAccurateValue(short[] a, short x) {
        if (a.length == 1) {
            return a[0];
        }
        short accurate = a[0];
        for (int i = 1; i < a.length; i++) {
            if (Math.abs(a[i] - x) < Math.abs(accurate - x)) {
                accurate = a[i];
            }
        }
        return accurate;
    }
}
