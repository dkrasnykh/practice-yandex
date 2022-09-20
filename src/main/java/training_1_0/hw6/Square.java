package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Square {
    static long rbinsearch(int l, int r) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (true) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            /*
            int n = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            long l = Long.parseLong(reader.readLine());
            */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
