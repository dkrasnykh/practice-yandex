package training_1_0.hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> seq = new ArrayList<>();
        while (true) {
            int next = in.nextInt();
            if (next == -2000000000) {
                System.out.println(getTypeOfSeq(seq));
                break;
            }
            seq.add(next);
        }
    }
    static String getTypeOfSeq(List<Integer> seq) {
        if (seq.size() == 0) {
            return "RANDOM";
        }
        if (seq.size() == 1) {
            return "CONSTANT";
        }
        boolean isIncrease = false;
        boolean isDecrease = false;
        boolean isEqual = false;
        for (int i = 1; i < seq.size(); i++) {
            int p = seq.get(i - 1);
            int c = seq.get(i);
            if (p < c) {
                isIncrease = true;
            } else if (p > c) {
                isDecrease = true;
            } else {
                isEqual = true;
            }
        }
        if(isEqual && !isIncrease && !isDecrease){
            return "CONSTANT";
        } else if (isIncrease && !isEqual && !isDecrease){
            return "ASCENDING";
        } else if (isIncrease && isEqual && !isDecrease){
            return "WEAKLY ASCENDING";
        } else if (isDecrease && !isEqual && !isIncrease){
            return "DESCENDING";
        } else if (isDecrease && isEqual && !isIncrease){
            return "WEAKLY DESCENDING";
        }
        return "RANDOM";
    }
}
