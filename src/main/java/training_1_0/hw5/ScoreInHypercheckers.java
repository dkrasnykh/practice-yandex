package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

public class ScoreInHypercheckers {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line1 = reader.readLine();
            int space = line1.indexOf(' ');
            int n = Integer.parseInt(line1.substring(0, space));
            int k = Integer.parseInt(line1.substring(space + 1));
            String[] xstr = regex.split(reader.readLine());
            long[] x = new long[n];
            for (int i = 0; i < n; i++) {
                x[i] = Long.parseLong(xstr[i]);
            }
            Arrays.sort(x);
            Map<Long, Long> cntnums = new TreeMap<>();
            for (int i = 0; i < x.length; i++) {
                cntnums.compute(x[i], (key, total) -> total == null ? 1 : total + 1);
            }
            List<Long> uniqnums = new ArrayList<>(cntnums.keySet());
            int r = 0;
            long ans = 0;
            long dublicates = 0;
            for (int l = 0; l < uniqnums.size(); l++) {
                while (r < uniqnums.size() && uniqnums.get(l) * k >= uniqnums.get(r)) {
                    if (cntnums.get(uniqnums.get(r)) >= 2) {
                        dublicates++;
                    }
                    r++;
                }
                long rangelen = r - l;
                if (cntnums.get(uniqnums.get(l)) >= 2) {
                    ans += (rangelen - 1) * 3;
                }
                if (cntnums.get(uniqnums.get(l)) >= 3) {
                    ans++;
                }
                ans += (rangelen - 1) * (rangelen - 2) * 3;
                if (cntnums.get(uniqnums.get(l)) >= 2) {
                    dublicates -= 1;
                }
                ans += dublicates * 3;
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
