package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Tourism {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int[] y = new int[n];
            Map<Integer, Integer> height = new HashMap<>();
            Map<Integer, Integer> down = new HashMap<>();
            height.put(1, 0);
            down.put(1, 0);
            int h = 0;
            int d = 0;
            String line = reader.readLine();
            int space = line.indexOf(' ');
            y[0] = Integer.parseInt(line.substring(space + 1));
            for (int i = 1; i < n; i++) {
                line = reader.readLine();
                space = line.indexOf(' ');
                y[i] = Integer.parseInt(line.substring(space + 1));
                if (y[i] > y[i - 1]) {
                    h += y[i] - y[i - 1];

                } else {
                    d += y[i - 1] - y[i];
                }
                height.put(i + 1, h);
                down.put(i + 1, d);
            }
            int m = Integer.parseInt(reader.readLine());
            int[] s = new int[m];
            int[] f = new int[m];
            for (int i = 0; i < m; i++) {
                line = reader.readLine();
                space = line.indexOf(' ');
                s[i] = Integer.parseInt(line.substring(0, space));
                f[i] = Integer.parseInt(line.substring(space + 1));
            }
            for (int i = 0; i < m; i++) {
                if (s[i] <= f[i]) {
                    writer.println(height.get(f[i]) - height.get(s[i]));
                } else {
                    writer.println(down.get(s[i]) - down.get(f[i]));
                }
            }
        }
    }
}
