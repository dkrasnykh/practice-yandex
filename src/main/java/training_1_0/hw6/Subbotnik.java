package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

public class Subbotnik {
    static boolean check(int m, int r, int c, int[] height) {
        List<int[]> segments = new ArrayList<>();
        for (int i = c - 1; i < height.length; i++) {
            if (height[i] - height[i - c + 1] <= m) {
                segments.add(new int[]{i - c + 1, i});
            }
        }
        Collections.sort(segments, Comparator.comparingInt(e -> e[1]));
        int count = 0;
        int finish = -1;
        for (int[] s : segments) {
            if (finish < s[0]) {
                finish = s[1];
                count++;
            }
        }
        return count >= r;
    }
    static int binsearch(int l, int r, int rc, int c, int[] height) {
        while (l < r) {
            int m = (r + l) / 2;
            if (check(m, rc, c, height)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] nrc = regex.split(reader.readLine());
            int n = Integer.parseInt(nrc[0]);
            int rc = Integer.parseInt(nrc[1]);
            int c = Integer.parseInt(nrc[2]);
            int[] height = new int[n];
            for (int i = 0; i < n; i++) {
                height[i] = Integer.parseInt(reader.readLine());
            }
            Arrays.sort(height);
            int l = Integer.MAX_VALUE;
            int r = 0;
            for (int i = c - 1; i < height.length; i++) {
                if (height[i] - height[i - c + 1] < l) {
                    l = height[i] - height[i - c + 1];
                }
                if (height[i] - height[i - c + 1] > r) {
                    r = height[i] - height[i - c + 1];
                }
            }
            int ans = binsearch(l, r, rc, c, height);
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
