package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class NumbersSum {
    public static void main(String[] args) throws IOException {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] nk = regex.split(reader.readLine());
            String[] numbstr = regex.split(reader.readLine());
            int n = Integer.parseInt(nk[0]);
            long k = Long.parseLong(nk[1]);
            Set<Long> prefixsum = new HashSet<>(n);
            prefixsum.add(0L);
            long nowSum = 0;
            int cnt = 0;
            for (int i = 0; i < numbstr.length; i++) {
                nowSum += Long.parseLong(numbstr[i]);
                prefixsum.add(nowSum);
                if (prefixsum.contains(nowSum - k)) {
                    cnt++;
                }
            }
            writer.println(cnt);
        }
    }
}
