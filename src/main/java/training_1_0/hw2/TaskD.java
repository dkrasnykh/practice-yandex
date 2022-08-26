package training_1_0.hw2;

import java.util.Scanner;

public class TaskD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        double[] a = getDoubleArrayFromString(input);
        System.out.println(getNumberOfElements(a));
    }
    static double[] getDoubleArrayFromString(String s) {
        if (s.isEmpty() || s == null) {
            return new double[0];
        }
        String[] sa = s.split(" ");
        double[] ans = new double[sa.length];
        for (int i = 0; i < sa.length; i++) {
            ans[i] = Double.parseDouble(sa[i]);
        }
        return ans;
    }
    static int getNumberOfElements(double[] a) {
        if (a.length < 3) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < a.length - 1; i++) {
            if (Double.compare(a[i - 1], a[i]) < 0 && Double.compare(a[i], a[i + 1]) > 0) {
                ans++;
            }
        }
        return ans;
    }
}
