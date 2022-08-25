package hw1;

import java.util.Scanner;

public class TaskI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int e = in.nextInt();

        if (a <= d && b <= e ||
                a <= e && b <= d ||
                a <= d && c <= e ||
                a <= e && c <= d ||
                b <= d && c <= e ||
                b <= e && c <= d) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}
