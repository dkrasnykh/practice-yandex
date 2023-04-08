package training_2_A.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class C_Girls_best_friends {
    static int lbinsearch1(int l, int r, long[] arr, long a, long b) {
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] * arr[m] > a * a + b * b) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l < arr.length && arr[l] * arr[l] > a * a + b * b) {
            return l;
        } else {
            return -1;
        }
    }

    static int lbinsearch2(int l, int r, long[] arr, long a, long b) {
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] >= a + b) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l<arr.length && arr[l] >= a + b) {
            return l;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine().trim());
            long[] d = new long[n];
            String[] line = reader.readLine().replaceAll("\\s+$", "").split(" ");
            for (int i = 0; i < n; ++i) {
                d[i] = Long.parseLong(line[i]);
            }
            long res = 0;
            for (int i = 0; i < d.length; ++i) {
                for (int j = i + 1; j < d.length; ++j) {
                    int ind1 = lbinsearch1(j + 1, n - 1, d, d[i], d[j]);
                    int ind2 = lbinsearch2(j + 1, n - 1, d, d[i], d[j]);
                    if (ind1 != -1 && ind2 != -1) {
                        res += (ind2 - ind1);
                    } else if (ind1 != -1) {
                        res += (n - ind1);
                    }
                }
            }
            writer.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
