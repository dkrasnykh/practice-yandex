package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class Pair implements Comparable {
    String name;
    String key;

    public Pair(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(name, pair.name) && Objects.equals(key, pair.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key);
    }

    @Override
    public int compareTo(Object o) {
        Pair p = (Pair) o;
        return this.name.compareTo(p.name) == 0 ? this.key.compareTo(p.key) : this.name.compareTo(p.name);
    }
}

public class Sales {
    //https://contest.yandex.ru/contest/27665/problems/F/
    static void print(Set<Pair> customers, Map<String, String> goods, Map<String, Long> counts) {
        Iterator<Pair> i = customers.iterator();
        String n = "";
        while (i.hasNext()) {
            Pair p = i.next();
            if (!p.name.equals(n)) {
                System.out.println(p.name+":");
            }
            System.out.println(goods.get(p.key) + " "+counts.get(p.key));
            n = p.name;
        }
    }

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Set<Pair> customers = new TreeSet<>();
            Map<String, String> goods = new HashMap<>();
            Map<String, Long> counts = new HashMap<>();
            while (true) {
                String data = reader.readLine();
                if (data == null) {
                    break;
                }
                String[] sale = regex.split(data);
                String key = sale[0] + sale[1];
                customers.add(new Pair(sale[0], key));
                goods.put(key, sale[1]);
                counts.merge(key, Long.parseLong(sale[2]), Long::sum);
            }
            reader.close();
            print(customers, goods, counts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}