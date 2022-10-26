package training_2_0_B.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class D_GuessTheNumber {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String line;
            String[] lines;
            Set<Integer> ans = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                ans.add(i);
            }
            Set<Integer> temp = new HashSet<>();
            while (true) {
                line = reader.readLine().trim();
                if (line.equals("HELP")) {
                    break;
                } else if (line.equals("YES")) {
                    ans.retainAll(temp);
                } else if (line.equals("NO")) {
                    ans.removeAll(temp);
                } else {
                    lines = line.split(" ");
                    temp = new HashSet<>();
                    for (int i = 0; i < lines.length; i++) {
                        temp.add(Integer.parseInt(lines[i]));
                    }
                }
            }
            writer.println(ans.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
