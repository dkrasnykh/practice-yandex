package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ImprovementInAcademicPerformance {
    public static long findNumberOfFives(long l, long r, long a, long b, long c) {
        while (l < r) {
            long m = (r + l) / 2;
            if (2 * (m * 5 + a * 2 + b * 3 + c * 4) >= 7 * (m + a + b + c)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            long a = Long.parseLong(reader.readLine());
            long b = Long.parseLong(reader.readLine());
            long c = Long.parseLong(reader.readLine());
            long k = findNumberOfFives(0, a + b + c, a, b, c);
            writer.println(k);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
