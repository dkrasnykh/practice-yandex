package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Subbotnik {
    static boolean check(int m, int rc, int c, int[] height) {
        int lastPos = -1;
        int lastPos1 = -1;

        int cnt = 0;
        int cnt1 = 0;
        int m1 = m - 1;
        int max=Integer.MAX_VALUE;
        int max1 = Integer.MAX_VALUE;
        System.out.println("---------------------------------------------------------------");
        System.out.println("m: " + m);
        for (int i = c - 1; i < height.length; i++) {
            if (height[i] - height[i - c + 1] >= m && i - c + 1 > lastPos) {
                max = height[i] - height[i - c + 1];
                cnt++;
                lastPos = i;
                System.out.println("height: " + height[i]);
            }

            if (height[i] - height[i - c + 1] >= m1 && i - c + 1 > lastPos1) {
                max1 = height[i] - height[i - c + 1];
                cnt1++;
                lastPos1 = i;
                //System.out.println("height: " + height[i]);
            }
        }
        System.out.println("cnt: " + cnt);
        System.out.println("cnt1: " + cnt1);
        return cnt >= rc && cnt1<cnt;
    }
    static int rbinsearch(int l, int r, int rc, int c, int[] height) {
        while (l < r) {
            int m = (r + l + 1) / 2;
            if (check(m, rc, c, height)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
    static int lbinsearch(int l, int r, int rc, int c, int[] height) {
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
            int r = height[n - 1] - height[0];

            int[] result = new int[rc + 1];
            int ans = rbinsearch(0, r, rc, c, height);
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
