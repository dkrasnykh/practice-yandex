package intensive.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(reader.readLine());
            int i = 1;
            while (n >= i) {
                n -= i;
                i++;
            }
            System.out.println(i - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
