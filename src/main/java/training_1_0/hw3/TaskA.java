package training_1_0.hw3;

import java.util.Set;
import java.util.Scanner;
import java.util.HashSet;

public class TaskA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Set<Long> set = getLongSetFromString(input);
        System.out.println(set.size());
    }
    static Set<Long> getLongSetFromString(String s) {
        Set<Long> set = new HashSet<>();
        if (s.isEmpty() || s == null) {
            return set;
        }
        String[] sa = s.split(" ");
        for (int i = 0; i < sa.length; i++) {
            set.add(Long.parseLong(sa[i]));
        }
        return set;
    }
}
