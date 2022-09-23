package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Median {
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
            int[][] l = new int[n][];
            String[] line;
            for (int i = 0; i < n; i++) {
                line = regex.split(reader.readLine());
                l[i] = new int[line.length];
                for (int j = 0; j < line.length; j++) {
                    l[i][j] = Integer.parseInt(line[j]);
                }
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i+1; j < n; j++) {
                    writer.println(getMedian(l[i], l[j]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
