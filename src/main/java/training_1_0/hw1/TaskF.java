package training_1_0.hw1;

import java.util.Scanner;

public class TaskF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        String[] inputArray = inputString.split(" ");
        int w1 = new Integer(inputArray[0]);
        int h1 = new Integer(inputArray[1]);
        int w2 = new Integer(inputArray[2]);
        int h2 = new Integer(inputArray[3]);

        int a;
        int b;
        int min;

        int a1;
        int b1;
        int s1;
        a1 = (w1 > w2) ? w1 : w2;
        b1 = h1 + h2;
        s1 = a1 * b1;

        a=a1;
        b=b1;
        min = s1;

        int a2;
        int b2;
        int s2;
        a2 = (w1 > h2) ? w1 : h2;
        b2 = h1 + w2;
        s2 = a2 * b2;

        if(s2<min){
            a=a2;
            b=b2;
            min=s2;
        }

        int a3;
        int b3;
        int s3;
        a3 = (h1 > w2) ? h1 : w2;
        b3 = w1 + h2;
        s3 = a3 * b3;

        if(s3<min){
            a=a3;
            b=b3;
            min=s3;
        }

        int a4;
        int b4;
        int s4;
        a4 = (h1 > h2) ? h1 : h2;
        b4 = w1 + w2;
        s4 = a4 * b4;

        if(s4<min){
            a=a4;
            b=b4;
        }

        System.out.println(a+" "+b);
    }
}
