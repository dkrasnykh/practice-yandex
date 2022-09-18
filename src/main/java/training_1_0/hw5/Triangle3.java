package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Triangle3 {
    public static int[] checkFirstElement(int[] c, int k) {
        int step;
        int j = 1;
        if (k % 2 != 0) {
            if (c[0] + 1 < c[1]) {
                c[0] += 1;
                step = 2;
            } else {
                step = 4;
            }
        } else {
            if (c[0] > 0) {
                c[0] -= 1;
                step = 2;
            } else {
                step = 5;
            }
        }
        return new int[]{step, j};
    }

    public static int[] decreasElement(int[] c, int j) {
        int step;
        if (c[j] > j) {
            c[j] = c[j - 1];
            c[j - 1] = j - 1;
            step = 2;
        } else {
            j += 1;
            step = 5;
        }
        return new int[]{step, j};
    }

    public static int[] enlargementElement(int[] c, int j, int k) {
        int step;
        if (c[j] + 1 <= c[j + 1]) {
            c[j - 1] = c[j];
            c[j] += 1;
            step = 2;
        } else {
            j += 1;
            if (j < k) {
                step = 4;
            } else {
                step = -1;
            }
        }
        return new int[]{step, j};
    }

    static boolean isIsoscelesTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        double e1 = ((double) (y3 - y1)) / (y2 - y1);
        double e2 = ((double) (x3 - x1)) / (x2 - x1);
        if (Math.abs(e1 - e2) < 0.00001) {
            return false;
        }
        double a = Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
        double b = Math.sqrt(Math.pow(Math.abs(x3 - x1), 2) + Math.pow(Math.abs(y3 - y1), 2));
        double c = Math.sqrt(Math.pow(Math.abs(x3 - x2), 2) + Math.pow(Math.abs(y3 - y2), 2));
        return Math.abs(a - b) < 0.00001 || Math.abs(a - c) < 0.00001 || Math.abs(b - c) < 0.00001;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int[] x = new int[n];
            int[] y = new int[n];
            String line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                int space = line.indexOf(' ');
                x[i] = Integer.parseInt(line.substring(0, space));
                y[i] = Integer.parseInt(line.substring(space + 1));
            }
            long cnt = 0;
            int k = 3;
            int[] c = new int[k + 1];
            for (int i = 0; i < k; i++) {
                c[i] = i;
            }
            c[k] = n - 1;
            int step = 2;
            int j = 1;
            for (; step != -1; ) {
                switch (step) {
                    case 2:
                        if (isIsoscelesTriangle(x[c[0]], y[c[0]], x[c[1]], y[c[1]], x[c[2]], y[c[2]])) {
                          cnt++;
                        }
                        step = 3;
                        break;
                    case 3:
                        int[] r3 = checkFirstElement(c, k);
                        step = r3[0];
                        j = r3[1];
                        break;
                    case 4:
                        int[] r4 = decreasElement(c, j);
                        step = r4[0];
                        j = r4[1];
                        break;
                    case 5:
                        int[] r5 = enlargementElement(c, j, k);
                        step = r5[0];
                        j = r5[1];
                        break;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
