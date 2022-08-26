package training_1_0.hw1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaskE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        String[] inputArray = inputString.split(" ");
        int k1 = new Integer(inputArray[0]);
        int m = new Integer(inputArray[1]);
        int k2 = new Integer(inputArray[2]);
        int p2 = new Integer(inputArray[3]);
        int n2 = new Integer(inputArray[4]);

        int n = n2 + (p2 - 1) * m;

        if (k2 < n || n2 > m) {
            System.out.println("-1 -1");
            return;
        }

        int pd;
        int nd;
        if (n == 1) {
            pd = (k1 <= m * k2) ? 1 : 0;
            nd = ((k1 < k2) || (m == 1)) ? 1 : 0;
            System.out.println(pd + " " + nd);
            return;
        }

        int[] p1n1;
        int check1 = (k2 - 1) / (n - 1);
        int check2 = (k2 % n != 0) ? k2 / n + 1 : k2 / n;
        if (check1 != check2) {
            Set<Integer> floors = new HashSet<>();
            Set<Integer> porches = new HashSet<>();
            //41 10 41 1 10
            if(check1<check2){
                System.out.println("-1 -1");
                return;
            }
            for (int i = check2; i <= check1; i++) {
                p1n1 = getP1N1(i, k1, m);
                porches.add(p1n1[0]);
                floors.add(p1n1[1]);
            }
            pd = (porches.size() == 1) ? porches.iterator().next() : 0;
            nd = (floors.size() == 1) ? floors.iterator().next() : 0;
            System.out.println(pd + " " + nd);
            return;
        }
        p1n1 = getP1N1(check2, k1, m);
        System.out.println(p1n1[0] + " " + p1n1[1]);
    }

    public static int[] getP1N1(int flatCountOnFloor, int k1, int m) {
        int floorForK1 = (k1 % flatCountOnFloor == 0) ? k1 / flatCountOnFloor : k1 / flatCountOnFloor + 1;
        int p1 = (floorForK1 % m == 0) ? floorForK1 / m : floorForK1 / m + 1;
        int n1 = floorForK1 - (p1 - 1) * m;
        return new int[]{p1, n1};
    }
}
