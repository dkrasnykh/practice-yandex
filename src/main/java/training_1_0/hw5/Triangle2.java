package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Combinations{
    private int m;
    private int n;
    public Combinations(int m, int n){
        this.m = m;
        this.n = n;
    }
    public int[] generateCombinations(int[] arr) {
        if (arr == null) {
            arr = new int[m];
            for (int i = 0; i < m; i++)
                arr[i] = i;
            return arr;
        }
        for (int i = m - 1; i >= 0; i--){
            if (arr[i] < n - m + i) {
                arr[i]++;
                for (int j = i; j < m - 1; j++)
                    arr[j + 1] = arr[j] + 1;
                return arr;
            }
        }
        return null;
    }
}

public class Triangle2 {
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
            Combinations combinations = new Combinations(3, n);
            int[] a = null;
            while((a = combinations.generateCombinations(a)) != null){
                if (isIsoscelesTriangle(x[a[0]], y[a[0]], x[a[1]], y[a[1]], x[a[2]], y[a[2]])) {
                    cnt++;
                }
            }
            writer.println(cnt);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
