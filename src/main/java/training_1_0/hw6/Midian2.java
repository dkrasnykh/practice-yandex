package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Midian2 {
    static int[] buildArray(int x1, int d1, int a, int c, int m, int l) {
        int[] d = new int[l];
        int[] x = new int[l];
        x[0] = x1;
        d[0] = d1;
        for (int i = 1; i < l; i++) {
            d[i] = (a * d[i - 1] + c) % m;
            x[i] = x[i - 1] + d[i - 1];
        }
        return x;
    }

    static int getMedian(int[] seq1, int[] seq2) {
        int lenght = seq1.length;
        int i = 0;
        int j = 0;
        int last = 0;
        for (int k = 0; k < lenght; k++) {
            if (i < lenght && seq1[i] < seq2[j] || j == lenght) {
                last = seq1[i];
                i++;
            } else {
                last = seq2[j];
                j++;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String nl = reader.readLine();
            int space = nl.indexOf(" ");
            int n = Integer.parseInt(nl.substring(0, space));
            int lv = Integer.parseInt(nl.substring(space + 1));
            int[][] l = new int[n][];
            String[] line;
            for (int i = 0; i < n; i++) {
                line = regex.split(reader.readLine());
                l[i] = buildArray(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]),
                        Integer.parseInt(line[3]), Integer.parseInt(line[4]), lv);
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    writer.println(getMedian(l[i], l[j]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
