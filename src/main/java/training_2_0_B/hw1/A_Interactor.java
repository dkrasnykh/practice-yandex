package training_2_0_B.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
https://contest.yandex.ru/contest/28730/problems/
*/
public class A_Interactor {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int r = Integer.parseInt(reader.readLine());
            int i = Integer.parseInt(reader.readLine());
            int c = Integer.parseInt(reader.readLine());

            switch (i) {
                case 0:
                    writer.println(r == 0 ? c : 3);
                    return;
                case 1:
                    writer.println(c);
                    return;
                case 4:
                    writer.println(r == 0 ? i : 3);
                    return;
                case 6:
                    writer.println(0);
                    return;
                case 7:
                    writer.println(1);
                    return;
                default:
                    writer.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
