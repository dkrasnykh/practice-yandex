package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class Sales {
    //https://contest.yandex.ru/contest/27665/problems/F/
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            Map<String, Long> map = new TreeMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                int secondSpace = line.lastIndexOf(' ');
                String nameAnsGoods = line.substring(0, secondSpace);
                int amount = Integer.parseInt(line.substring(secondSpace + 1));

                map.compute(nameAnsGoods, (key, sum) -> sum == null ? amount : sum + amount);
            }

            String currentName = null;
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                String nameAndGoods = entry.getKey();
                Long totals = entry.getValue();
                int space = nameAndGoods.indexOf(' ');
                String name = nameAndGoods.substring(0, space);
                String goods = nameAndGoods.substring(space + 1);

                if (!name.equals(currentName)) {
                    writer.print(name);
                    writer.println(':');
                    currentName = name;
                }

                writer.print(goods);
                writer.print(' ');
                writer.println(totals);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}