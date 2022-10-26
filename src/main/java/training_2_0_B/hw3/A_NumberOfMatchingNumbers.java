package training_2_0_B.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class A_NumberOfMatchingNumbers {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line1 = reader.readLine().trim().split(" ");
            String[] line2 = reader.readLine().trim().split(" ");
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int i = 0; i < line1.length; i++) {
                set1.add(Integer.parseInt(line1[i]));
            }
            for (int i = 0; i < line2.length; i++) {
                set2.add(Integer.parseInt(line2[i]));
            }
            set1.retainAll(set2);
            writer.println(set1.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
