package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BankAccounts {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {

            Map<String, Long> map = new HashMap<>();

            String input;
            while ((input = reader.readLine()) != null) {
                String[] data = regex.split(input);
                long amount;
                int p;
                String operation = data[0];
                switch (operation) {
                    case "DEPOSIT":
                        amount = Long.parseLong(data[2]);
                        map.compute(data[1], (key, total) -> (total == null) ? amount : total + amount);
                        break;
                    case "WITHDRAW":
                        amount = Long.parseLong(data[2]);
                        map.compute(data[1], (key, total) -> (total == null) ? -amount : total - amount);
                        break;
                    case "BALANCE":
                        writer.println((map.containsKey(data[1]) ? map.get(data[1]) : "ERROR"));
                        break;
                    case "TRANSFER":
                        amount = Long.parseLong(data[3]);
                        map.compute(data[1], (key, total) -> (total == null) ? -amount : total - amount);
                        map.compute(data[2], (key, total) -> (total == null) ? amount : total + amount);
                        break;
                    case "INCOME":
                        p = Integer.parseInt(data[1]);
                        List<String> clients = map.entrySet().stream().filter(e -> e.getValue() > 0).map(Map.Entry::getKey).collect(Collectors.toList());
                        for (String c : clients) {
                            map.compute(c, (key, total) -> total + (long) (((double) total / 100) * p));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
