package training_1_0.hw7.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Exam {
    public static int findUnavailableStudent(int l, int r, int d, int[] x) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (x[m] < d) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine();
            int space = line.indexOf(' ');
            int n = Integer.parseInt(line.substring(0, space));
            int d = Integer.parseInt(line.substring(space + 1));
            String[] s = regex.split(reader.readLine());
            Map<Integer, Integer> numbers = new HashMap<>();
            int[] x = new int[n];
            int[] xsort = new int[n + 1];
            xsort[0] = -1;
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(s[i]);
                xsort[i+1] = x[i];
            }
            Arrays.sort(xsort);
            int max = 0;
            for (int i = 1; i < xsort.length; i++) {
                int r = findUnavailableStudent(0, i, xsort[i] - d, xsort);
                if (i - r > max) {
                    max = i - r;
                }
            }
            for (int i = 1; i < xsort.length; i++) {
                numbers.put(xsort[i], (i) % max == 0 ? max : (i) % max);
            }
            writer.println(max);
            for (int i = 0; i < x.length; i++) {
                writer.print(numbers.get(x[i]) + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
