package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Keyboard {
    //https://contest.yandex.ru/contest/27665/problems/D/
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            int[] c = new int[n + 1];
            String[] input = regex.split(reader.readLine());
            for (int i = 0; i < input.length; i++) {
                c[i + 1] = Integer.parseInt(input[i]);
            }
            int k = Integer.parseInt(reader.readLine());
            input = regex.split(reader.readLine());
            for (int i = 1; i <= k; i++) {
                int p = Integer.parseInt(input[i - 1]);
                c[p]--;
            }
            for (int i = 1; i <= n; i++) {
                System.out.println((c[i] < 0) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
