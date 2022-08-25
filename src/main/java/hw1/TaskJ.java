package hw1;

import java.util.Scanner;

public class TaskJ {
    public static void main(String[] args) {
        //https://contest.yandex.ru/contest/27393/enter/
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        double d = in.nextDouble();
        double e = in.nextDouble();
        double f = in.nextDouble();

        double det = a * d - b * c;
        if (Math.abs(det) > 0.00001) {
            double det1 = e * d - f * b;
            double det2 = a * f - c * e;
            double x = det1 / det;
            double y = det2 / det;
            System.out.println("2 " + ((x % 1 == 0) ? String.format("%.0f", x) : x) + " " + ((y % 1 == 0) ? String.format("%.0f", y) : y));
            return;
        }

        if (Math.abs(a) < 0.00001 && Math.abs(b) < 0.00001 && Math.abs(c) < 0.00001 && Math.abs(d) < 0.00001) {
            if (Math.abs(e) < 0.00001 && Math.abs(f) < 0.00001) {
                System.out.println(5);
            } else {
                System.out.println(0);
            }
            return;
        }

        if (Math.abs(a) < 0.00001 && Math.abs(b) < 0.00001 && Math.abs(e) < 0.00001) {
            if(Math.abs(c) < 0.00001){
                double y0 = f / d;
                System.out.println("4 " + ((y0 % 1 == 0) ? String.format("%.0f", y0) : y0));
                return;
            }
            if(Math.abs(d) < 0.00001){
                double x0 = f / c;
                System.out.println("3 " + ((x0 % 1 == 0) ? String.format("%.0f", x0) : x0));
                return;
            }
            double kx = -c / d;
            double kb = f / d;
            System.out.println("1 " + ((kx % 1 == 0) ? String.format("%.0f", kx) : kx) + " " + ((kb % 1 == 0) ? String.format("%.0f", kb) : kb));
            return;
        }

        if(Math.abs(c) < 0.00001 && Math.abs(d) < 0.00001 && Math.abs(f) < 0.00001){
            if(Math.abs(a) < 0.00001){
                double y0 = e / b;
                System.out.println("4 " + ((y0 % 1 == 0) ? String.format("%.0f", y0) : y0));
                return;
            }
            if(Math.abs(b) < 0.00001){
                double x0 = e / a;
                System.out.println("3 " + ((x0 % 1 == 0) ? String.format("%.0f", x0) : x0));
                return;
            }
            double kx = -a / b;
            double kb = e / b;
            System.out.println("1 " + ((kx % 1 == 0) ? String.format("%.0f", kx) : kx) + " " + ((kb % 1 == 0) ? String.format("%.0f", kb) : kb));
            return;
        }


        if (Math.abs(a) < 0.00001 && Math.abs(c) < 0.00001) {
            double y0 = e / b;
            if (Math.abs(d * y0 - f) < 0.00001) {
                System.out.println("4 " + ((y0 % 1 == 0) ? String.format("%.0f", y0) : y0));
            } else {
                System.out.println(0);
            }
            return;
        }
        if (Math.abs(b) < 0.00001 && Math.abs(d) < 0.00001) {
            double x0 = e / a;
            if (Math.abs(c * x0 - f) < 0.00001) {
                System.out.println("3 " + ((x0 % 1 == 0) ? String.format("%.0f", x0) : x0));
            } else {
                System.out.println(0);
            }
            return;
        }
        double k = c / a;
        if (Math.abs(k * b - d) < 0.00001) {
            if (Math.abs(k * e - f) < 0.00001) {
                double kx = -a / b;
                double kb = e / b;
                System.out.println("1 " + ((kx % 1 == 0) ? String.format("%.0f", kx) : kx) + " " + ((kb % 1 == 0) ? String.format("%.0f", kb) : kb));
            } else {
                System.out.println(0);
            }
            return;
        }
        System.out.println(0);
    }
}
