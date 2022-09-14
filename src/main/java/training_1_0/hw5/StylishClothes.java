package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class StylishClothes {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String[] nscolor = regex.split(reader.readLine());
            long[] ncolor = new long[n];
            for (int i = 0; i < n; i++) {
                ncolor[i] = Long.parseLong(nscolor[i]);
            }
            int m = Integer.parseInt(reader.readLine());
            String[] mscolor = regex.split(reader.readLine());
            long[] mcolor = new long[m];
            for (int i = 0; i < m; i++) {
                mcolor[i] = Long.parseLong(mscolor[i]);
            }
            int i = 0;
            int j = 0;
            int nmax = 0;
            int mmax = 0;
            long d = Math.abs(ncolor[0] - mcolor[0]);
            if (d == 0) {
                writer.println(ncolor[0] + " " + mcolor[0]);
                return;
            }
            for (int k = 0; k < ncolor.length + mcolor.length; k++) {
                if (i < ncolor.length && j < mcolor.length && d > Math.abs(ncolor[i] - mcolor[j])) {
                    nmax = i;
                    mmax = j;
                    d = Math.abs(ncolor[i] - mcolor[j]);
                }
                if (i != ncolor.length && j == mcolor.length || i < ncolor.length && j < mcolor.length && ncolor[i] <= mcolor[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            writer.println(ncolor[nmax] + " " + mcolor[mmax]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
