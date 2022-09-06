package intensive.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class D1 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            String[] priceStr = regex.split(reader.readLine());
            int[] price = new int[n];

            for (int i = 0; i < priceStr.length; i++) {
                price[i] = Integer.parseInt(priceStr[i]);
            }
            int i1 = 0;
            int i2 = 0;
            int maxSum = 0;
            for (int i = 0; i < price.length - 1; i++) {
                for (int j = i + 1; j < price.length; j++) {
                    int k = 1000 / price[i];
                    int k2 = 1000 % price[i];
                    int p = price[j] * k + k2 - 1000;
                    if (p > maxSum) {
                        i1 = i;
                        i2 = j;
                        maxSum = p;
                    }
                }
            }
            if (maxSum > 0) {
                System.out.println((i1 + 1) + " " + (i2 + 1));
            } else {
                System.out.println("0 0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
