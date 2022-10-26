package training_2_0_B.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class C_UniqueElements {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line1 = reader.readLine().trim().split(" ");
            int[] a = new int[line1.length];
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> duplicates = new HashSet<>();
            for (int i = 0; i < line1.length; i++) {
                int n = Integer.parseInt(line1[i]);
                a[i] = n;
                if (set1.contains(n)) {
                    duplicates.add(n);
                }
                set1.add(n);
            }
            for (int i = 0; i < a.length; i++) {
                if (!duplicates.contains(a[i])) {
                    writer.print(a[i] + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
