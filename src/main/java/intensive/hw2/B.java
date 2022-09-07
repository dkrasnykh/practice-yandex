package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class B {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            String[] inStr = regex.split(reader.readLine());
            int n = Integer.parseInt(inStr[0]);
            int k = Integer.parseInt(inStr[1]);
            inStr = regex.split(reader.readLine());
            int[] anum = new int[n];
            Set<Integer> snum = new HashSet<>();
            List<Integer> repeat = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                anum[i] = Integer.parseInt(inStr[i]);
                if (snum.contains(anum[i])) {
                    if (!repeat.contains(anum[i])) {
                        repeat.add(anum[i]);
                    }
                }
                snum.add(anum[i]);
            }
            for (Integer r : repeat) {
                int i1 = -1;
                for (int i = 0; i < anum.length; i++) {
                    if (anum[i] == r) {
                        if (i1 != -1 && i - i1 <= k) {
                            System.out.println("YES");
                            return;
                        } else {
                            i1 = i;
                        }
                    }
                }
            }
            System.out.println("NO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
