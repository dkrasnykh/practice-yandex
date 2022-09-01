package training_1_0.hw3;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        Set<Long> set1 = getLongSetFromString(line1);
        Set<Long> set2 = getLongSetFromString(line2);
        set1.retainAll(set2);
        List<Long> ans = set1.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < ans.size() - 1; i++) {
            System.out.print(ans.get(i) + " ");
        }
        if (ans.size() > 0) {
            System.out.println(ans.get(ans.size() - 1));
        }
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
