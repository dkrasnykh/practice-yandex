package training_2_0_B.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class B_NumberWasBefore {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line1 = reader.readLine().trim().split(" ");
            Set<Integer> set1 = new HashSet<>();
            String[] ans = new String[line1.length];
            for (int i = 0; i < line1.length; i++) {
                int n = Integer.parseInt(line1[i]);
                ans[i] = (set1.contains(n)) ? "YES" : "NO";
                set1.add(n);
            }
            for (int i = 0; i < ans.length; i++) {
                writer.println(ans[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
