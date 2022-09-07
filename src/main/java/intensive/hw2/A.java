package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class A {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            String[] numbersStr = regex.split(reader.readLine());
            Set<Integer> numbers1 = new HashSet<>();
            for (int i = 0; i < numbersStr.length; i++) {
                numbers1.add(Integer.parseInt(numbersStr[i]));
            }
            n = Integer.parseInt(reader.readLine());
            numbersStr = regex.split(reader.readLine());
            Set<Integer> numbers2 = new HashSet<>();
            for (int i = 0; i < numbersStr.length; i++) {
                numbers2.add(Integer.parseInt(numbersStr[i]));
            }
            n = Integer.parseInt(reader.readLine());
            numbersStr = regex.split(reader.readLine());
            Set<Integer> numbers3 = new HashSet<>();
            for (int i = 0; i < numbersStr.length; i++) {
                numbers3.add(Integer.parseInt(numbersStr[i]));
            }
            Set<Integer> a = new HashSet<>(numbers1);
            Set<Integer> c = new HashSet<>(numbers3);

            numbers1.retainAll(numbers2);
            numbers3.retainAll(numbers2);
            a.retainAll(c);
            numbers1.addAll(numbers3);
            numbers1.addAll(a);

            numbers1.stream().sorted().forEach(s -> System.out.print(s + " "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
