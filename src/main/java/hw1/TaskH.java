package hw1;

import java.util.Scanner;

public class TaskH {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();

        int t1max = n + (n - 1) * a + 2 * a;
        int t1min = n + (n - 1) * a;
        int t2max = m + (m - 1) * b + 2 * b;
        int t2min = m + (m - 1) * b;

        if (t1min > t2max || t2min > t1max) {
            System.out.println(-1);
            return;
        }

        int tmin = (t1min > t2min) ? t1min : t2min;
        int tmax = (t1max < t2max) ? t1max : t2max;
        System.out.println(tmin + " " + tmax);
    }
}
