package training_2_0_B.hw7.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class B_Customs2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            List<int[]> events = new ArrayList<>(n);
            String[] line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine().split(" ");
                int t = Integer.parseInt(line[0]);
                int l = Integer.parseInt(line[1]);
                events.add(new int[]{t, 1, i});
                events.add(new int[]{t + l, -1, i});
            }
            Map<Integer, Integer> map = new HashMap<>();
            Collections.sort(events, ((o1, o2) -> (o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0])));
            int cnt = 0;
            int open;
            int maxopen = 0;
            for (int[] e : events) {
                if (e[1] == 1) {
                    cnt++;
                } else if (e[1] == -1) {
                    open = cnt + map.getOrDefault(e[1], 0);
                    map.compute(e[0], (key, value) -> (value == null) ? 1 : value + 1);
                    if (maxopen < open) {
                        maxopen = open;
                    }
                    cnt--;
                }
            }
            writer.println(maxopen);
        } catch (IOException e) {

        }
    }
}
