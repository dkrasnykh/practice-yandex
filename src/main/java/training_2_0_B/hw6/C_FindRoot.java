package training_2_0_B.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class C_FindRoot {
    static boolean check(int a, int b, int c, int d, double root, double eps) {
        //Math.abs(det) > 0.00001
        return Math.abs(a * root * root * root + b * root * root + c * root + d) == 0;
    }

    static double fbinsearch(double l, double r, double eps, int a, int b, int c, int d) {
        while (l+eps < r) {
            double m = (l + r) / 2;
            if (check(a, b, c, d, m, eps)) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);
            int d = Integer.parseInt(line[3]);
            double eps = 0.000001;
            writer.println(fbinsearch(-1000, 1000, eps, a, b, c, d));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}