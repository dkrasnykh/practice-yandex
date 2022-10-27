package training_2_0_B.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class B_MaxSum {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String[] line = reader.readLine().trim().split(" ");
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(line[i]);
            }
            long currSum = 0;
            long maxSum = Long.MIN_VALUE;
            for (int endIndex = 0; endIndex < a.length; endIndex++) {
                currSum += a[endIndex];
                if (currSum > maxSum) {
                    maxSum = currSum;
                }
                if (currSum < 0) {
                    currSum = 0;
                }
            }
            writer.println(maxSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
