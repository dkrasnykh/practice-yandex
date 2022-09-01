package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class TaskC {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] in = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(in[0]);
            int m = Integer.parseInt(in[1]);
            TreeSet<Integer> set1 = new TreeSet<>();
            TreeSet<Integer> set2 = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                set1.add(Integer.parseInt(reader.readLine()));
            }
            for (int i = 0; i < m; i++) {
                set2.add(Integer.parseInt(reader.readLine()));
            }
            TreeSet<Integer> intersection = (TreeSet<Integer>) set1.clone();
            intersection.retainAll(set2);
            print(intersection);
            set1.removeAll(intersection);
            print(set1);
            set2.removeAll(intersection);
            print(set2);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static void print(TreeSet<Integer> set) {
        System.out.println(set.size());
        String[] aStr = Arrays.stream(set.toArray()).map(String::valueOf).toArray(String[]::new);
        System.out.println(String.join(" ", aStr));
    }
}
