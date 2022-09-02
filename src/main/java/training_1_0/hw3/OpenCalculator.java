package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
/*
https://contest.yandex.ru/contest/27663/problems/E/
*/
public class OpenCalculator {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String xyzStr = reader.readLine();
            String[] xyzArr = xyzStr.split(" ");
            String n = reader.readLine();

            Set<Integer> calcButtons = new HashSet<>();
            Set<Integer> inputNumbers = new HashSet<>();

            for (int i = 0; i < xyzArr.length; i++) {
                calcButtons.add(Integer.parseInt(xyzArr[i]));
            }

            for (int i = 0; i < n.length(); i++) {
                inputNumbers.add(Character.getNumericValue(n.charAt(i)));
            }
            inputNumbers.removeAll(calcButtons);

            System.out.println(inputNumbers.size());
        }
    }
}
