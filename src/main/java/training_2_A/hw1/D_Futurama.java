package training_2_A.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class D_Futurama {
    private static void swap(int b1, int b2, int[] mapping) {
        int tmp = mapping[b1];
        mapping[b1] = mapping[b2];
        mapping[b2] = tmp;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int[] mapping = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                mapping[i] = i;
            }
            for (int i = 0; i < m; ++i) {
                line = reader.readLine().trim().split(" ");
                int b1 = Integer.parseInt(line[0]);
                int b2 = Integer.parseInt(line[1]);
                swap(b1, b2, mapping);
            }
            int k = 0;

            for (int i = 1; i < n - 1; ++i) {
                if (mapping[i] != i) {
                    k += 1;
                }
            }
            if (k == 0) {
                return;
            }
            List<int[]> ans = new ArrayList<>();
            int j = 1;
            while (k > 1) {
                j = 1;
                while (mapping[j] == j) {
                    j += 1;
                }
                while (k > 1) {
                    ans.add(new int[]{j, n - 1});
                    swap(j, n - 1, mapping);
                    j = mapping[n - 1];
                    if (mapping[mapping[mapping[n - 1]]] == n - 1 || mapping[mapping[mapping[n - 1]]] == n) {
                        break;
                    }
                    --k;
                }
                ans.add(new int[]{j, n});
                swap(j, n, mapping);
                j = mapping[n];

                ans.add(new int[]{j, n});
                swap(j, n, mapping);
                j = mapping[n - 1];

                ans.add(new int[]{j, n - 1});
                swap(j, n - 1, mapping);
                k -= 2;
            }
            if (mapping[n - 1] == n) {
                ans.add(new int[]{n - 1, n});
                swap(n - 1, n, mapping);
            }
            for (int[] e : ans) {
                writer.println(e[0] + " " + e[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
