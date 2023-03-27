package training_2_A.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class E_Names {
    static int lbinsearch(int l, int r, int param, List<Integer> a) {
        while (l < r) {
            int m = (l + r) / 2;
            if (a.get(m) > param) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if(a.get(l) > param){
            return l;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String s1 = reader.readLine().trim();
            String s2 = reader.readLine().trim();
            char[] name1, name2;
            if (s1.length() <= s2.length()) {
                name1 = s2.toCharArray();
                name2 = s1.toCharArray();
            } else {
                name1 = s1.toCharArray();
                name2 = s2.toCharArray();
            }
            Map<Character, List<Integer>> cnt1 = new HashMap<>();
            Map<Character, List<Integer>> cnt2 = new HashMap<>();
            for (int i = 0; i < name1.length; ++i) {
                if (cnt1.containsKey(name1[i])) {
                    cnt1.get(name1[i]).add(i);
                } else {
                    List<Integer> e = new ArrayList<>(List.of(i));
                    cnt1.put(name1[i], e);
                }
            }
            for (int i = 0; i < name2.length; ++i) {
                if (cnt2.containsKey(name2[i])) {
                    cnt2.get(name2[i]).add(i);
                } else {
                    List<Integer> e1 = new ArrayList<>(List.of(i));
                    cnt2.put(name2[i], e1);
                }
            }
            Arrays.sort(name2);
            StringBuilder ans = new StringBuilder();
            int ind1 = -1, ind2 = -1;
            for (int i = name2.length - 1; i >= 0; --i) {
                if (cnt1.containsKey(name2[i])) {
                    int i1 = lbinsearch(0, cnt1.get(name2[i]).size()-1, ind1, cnt1.get(name2[i]));
                    int i2 = lbinsearch(0, cnt2.get(name2[i]).size()-1, ind2, cnt2.get(name2[i]));
                    if (i1 != -1 && i2 != -1) {
                        ind1 = cnt1.get(name2[i]).get(i1);
                        ind2 = cnt2.get(name2[i]).get(i2);
                        ans.append(name2[i]);
                    }
                }
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
