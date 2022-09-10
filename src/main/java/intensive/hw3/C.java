package intensive.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class C {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            reader.readLine();
            String[] ns = regex.split(reader.readLine());
            int[] n = new int[ns.length];
            int[] prefixpos = new int[n.length + 1];
            prefixpos[0] = 0;
            for (int i = 0; i < ns.length; i++) {
                n[i] = Integer.parseInt(ns[i]);
                prefixpos[i + 1] = prefixpos[i] + ((n[i] > 0) ? 1 : 0);
            }
            int q = Integer.parseInt(reader.readLine());
            int[] l = new int[q];
            int[] r = new int[q];
            for (int i = 0; i < q; i++) {
                ns = regex.split(reader.readLine());
                l[i] = Integer.parseInt(ns[0]);
                r[i] = Integer.parseInt(ns[1]);
            }
            for (int i = 0; i < q; i++) {
                System.out.println(prefixpos[r[i]]-prefixpos[l[i]-1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
