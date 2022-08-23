package hw1;

import java.util.Scanner;

public class TaskD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        if (c < 0 || (a == 0 && b != c * c) || (a != 0 && ((c * c - b) % a) !=0)) {
            System.out.println("NO SOLUTION");
            return;
        }
        if (a == 0 && b == c * c) {
            System.out.println("MANY SOLUTIONS");
            return;
        }
        int x = (c * c - b) / a;
        if (a * x + b == c * c) {
            System.out.println(x);
        } else {
            System.out.println("NO SOLUTION");
        }
    }
}
