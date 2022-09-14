package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class CheCity {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] nr = regex.split(reader.readLine());
            int n = Integer.parseInt(nr[0]);
            int r = Integer.parseInt(nr[1]);
            String[] dstr = regex.split(reader.readLine());
            int[] d = new int[n];
            for (int i = 0; i < dstr.length; i++) {
                d[i] = Integer.parseInt(dstr[i]);
            }
            long cnt = 0;
            int last = 0;
            for (int first = 0; first < d.length; first++) {
                while (last < d.length && d[last] - d[first] <= r) {
                    last++;
                }
                cnt += n - last;
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
