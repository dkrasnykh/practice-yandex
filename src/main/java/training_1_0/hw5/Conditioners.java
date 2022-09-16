package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Conditioners {
    public static void main(String[] args) throws IOException {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String[] nstr = regex.split(reader.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(nstr[i]);
            }
            int m = Integer.parseInt(reader.readLine());
            int[][] b = new int[m][2];
            for (int i = 0; i < m; i++) {
                String line = reader.readLine();
                int space = line.indexOf(' ');
                b[i][0] = Integer.parseInt(line.substring(0, space));
                b[i][1] = Integer.parseInt(line.substring(space + 1));
            }
            Arrays.sort(a);
            Arrays.sort(b, (e1, e2) -> e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]);
            int min1 = b[b.length - 1][1];
            for (int i = b.length - 1; i >= 0; i--) {
                if (b[i][1] > min1) {
                    b[i][1] = min1;
                } else {
                    min1 = b[i][1];
                }
            }
            int last = 0;
            int cost = 0;
            for (int first = 0; first < a.length; first++) {
                while (last < b.length && a[first] > b[last][0]) {
                    last++;
                }
                cost += b[last][1];
            }
            writer.println(cost);
        }
    }
}
