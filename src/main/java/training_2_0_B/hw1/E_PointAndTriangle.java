package training_2_0_B.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class E_PointAndTriangle {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int d = Integer.parseInt(reader.readLine());
            String[] line = reader.readLine().trim().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            if (x + y <= d && x >= 0 && y >= 0) {
                writer.println(0);
                return;
            }
            int r1 = Math.abs(x) + Math.abs(y);
            int r2 = Math.abs(x - d) + Math.abs(y);
            int r3 = Math.abs(x) + Math.abs(y - d);
            int min = 0;
            int nmin = 0;
            if (r1 <= r2) {
                min = r1;
                nmin = 1;
            } else {
                min = r2;
                nmin = 2;
            }
            if (r3 < min) {
                nmin = 3;
            }
            writer.println(nmin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
