package training_1_0.hw1;

import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine();
        String mode = in.nextLine();
        String[] tempSplit = line1.split(" ");
        Integer troom = new Integer(tempSplit[0]);
        Integer tcond = new Integer(tempSplit[1]);

        switch (mode) {
            case ("freeze"):
                System.out.println((troom > tcond) ? tcond : troom);
                break;
            case ("heat"):
                System.out.println((tcond > troom) ? tcond : troom);
                break;
            case ("auto"):
                System.out.println(tcond);
                break;
            case ("fan"):
                System.out.println(troom);
                break;
        }
        in.close();
    }
}
