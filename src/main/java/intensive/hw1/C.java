package intensive.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class C {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            String[] costStr = regex.split(reader.readLine());
            int[] cost = new int[costStr.length];
            for (int i = 0; i < costStr.length; i++) {
                cost[i] = Integer.parseInt(costStr[i]);
            }
            int bestBuyDay = 0;
            int bestSellDay = 0;
            int minCostDay = 0;
            for (int i = 0; i < cost.length; i++) {
                if (cost[bestSellDay] * cost[minCostDay] < cost[bestBuyDay] * cost[i]) {
                    bestBuyDay = minCostDay;
                    bestSellDay = i;
                }
                if (cost[i] < cost[minCostDay]) {
                    minCostDay = i;
                }
            }
            if (bestSellDay == 0 && bestBuyDay == 0) {
                System.out.println(0 + " " + 0);
            } else {
                System.out.println((bestBuyDay + 1) + " " + (bestSellDay + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
