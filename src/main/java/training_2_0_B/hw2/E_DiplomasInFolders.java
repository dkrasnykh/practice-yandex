package training_2_0_B.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class E_DiplomasInFolders {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int N = Integer.parseInt(reader.readLine());
            String[] line = reader.readLine().trim().split(" ");
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(line[i]);
            }
            Arrays.sort(a);
            int time = 0;
            for (int i = 0; i < a.length - 1; i++) {
                time += a[i];
            }
            writer.println(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
