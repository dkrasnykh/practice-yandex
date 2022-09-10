package intensive.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

public class C1 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            String[] lnm = regex.split(reader.readLine());
            int l = Integer.parseInt(lnm[0]);
            int n = Integer.parseInt(lnm[1]);
            int m = Integer.parseInt(lnm[2]);
            List<int[]> events = new ArrayList<>(n * 2 + m);
            String[] input;
            for (int i = 0; i < n; i++) {
                input = regex.split(reader.readLine());
                events.add(new int[]{Integer.parseInt(input[0]), -1});
                events.add(new int[]{Integer.parseInt(input[1]), 1});
            }

            int[] checkPos = new int[m];
            for (int i = 0; i < m; i++) {
                checkPos[i] = Integer.parseInt(reader.readLine());
                events.add(new int[]{checkPos[i], 0});
            }
            Collections.sort(events, (o1, o2) -> (o1[0] == o2[0]) ? o1[1] - o2[1] : o1[0] - o2[0]);
            Map<Integer, Integer> coveringInPoints = new HashMap<>(m);
            int covering = 0;
            for (int[] s : events) {
                if (s[1] == -1) {
                    covering ++;
                } else if (s[1] == 1) {
                    covering --;
                } else {
                    coveringInPoints.put(s[0], covering);
                }
            }
            for (int i = 0; i < checkPos.length; i++) {
                System.out.println(coveringInPoints.get(checkPos[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
