package hw1;

import java.util.Scanner;

public class TaskG {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        String[] inputArray = inputString.split(" ");
        int n = new Integer(inputArray[0]);
        int k = new Integer(inputArray[1]);
        int m = new Integer(inputArray[2]);
        if (k > n || m > k) {
            System.out.println(0);
            return;
        }
        System.out.println(getNumberOfDetails(n,k, m, 0));
    }

    public static int getNumberOfDetails(int s, int k, int m, int km){
        if(s<k){
            return km;
        }
        int nk = s / k;
        s -= nk * k;
        km += ((k / m) * nk);
        s += ((k % m) * nk);
        return getNumberOfDetails(s, k, m, km);
    }
}