package training_1_0.hw2;

import java.util.Scanner;

public class TaskJ {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        double[] a = new double[n];
        String[] b = new String[n];
        a[0] = Double.parseDouble(in.nextLine());
        for (int i = 1; i < n; i++) {
            String nextLine = in.nextLine();
            String[] parseLine = nextLine.split(" ");
            a[i] = Double.parseDouble(parseLine[0]);
            b[i] = parseLine[1];
        }
        double[] points1 = new double[n];
        double[] points2 = new double[n];
        points1[0] = 30;
        points2[0] = 4000;

        for (int i = 1; i < a.length; i++) {
            if (b[i].equals("closer")) {
                if (Math.abs(a[i] - a[i - 1]) < 0.0000001) {
                    points1[i] = points1[i - 1];
                    points2[i] = points2[i - 1];
                } else if (a[i] > a[i - 1]) {
                    points1[i] = (a[i] + a[i - 1]) / 2;
                    points2[i] = 4000;
                } else {
                    points1[i] = 30;
                    points2[i] = (a[i] + a[i - 1]) / 2;
                }
            }

            if (b[i].equals("further")) {
                if (Math.abs(a[i] - a[i - 1]) < 0.0000001) {
                    points1[i] = points1[i - 1];
                    points2[i] = points2[i - 1];
                } else if (a[i] > a[i - 1]) {
                    points1[i] = 30;
                    points2[i] = (a[i] + a[i - 1]) / 2;
                } else {
                    points1[i] = (a[i] + a[i - 1]) / 2;
                    points2[i] = 4000;
                }
            }
        }
        double max = points1[0];
        double min = points2[0];
        for (int i = 0; i < n; i++) {
            if (points1[i] > max) {
                max = points1[i];
            }
            if (points2[i] < min) {
                min = points2[i];
            }
        }
        System.out.println(max + " " + min);
    }
}
