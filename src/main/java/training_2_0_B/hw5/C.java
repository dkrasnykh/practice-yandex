package training_2_0_B.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class C {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[][] x = new int[n][2];
            int[][] y = new int[m][2];
            line = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                x[i][0] = Integer.parseInt(line[i]);
                x[i][1] = i + 1;
            }
            line = reader.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                y[i][0] = Integer.parseInt(line[i]);
                y[i][1] = i + 1;
            }
            Arrays.sort(x, Comparator.comparingInt(x2 -> x2[0]));
            Arrays.sort(y, Comparator.comparingInt(y2 -> y2[0]));
            int[] ans = new int[n + 1];
            int igroup = 0;
            int iroom = 0;
            int p = 0;
            while (igroup < n && iroom < m) {
                if (x[igroup][0] < y[iroom][0]) {
                    ans[x[igroup][1]] = y[iroom][1];
                    igroup++;
                    iroom++;
                    p++;
                } else {
                    iroom++;
                }
            }
            writer.println(p);
            for (int i = 1; i < ans.length; i++) {
                writer.print(ans[i] + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
