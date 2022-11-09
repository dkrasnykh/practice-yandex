package training_2_0_B.hw7.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class B_Customs {
    static int binsearch(int l, int r, int k, Integer[] a){
        while(l<r){
            int m = (l + r + 1) / 2;
            if(a[m]<=k){
                l = m;
            } else {
                r = m - 1;
            }
        }
        if(a.length==0 || (l==0 || l==a.length-1) && a[l]>k){
            return -1;
        }
        return l;
    }

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
                events.add(new int[]{t, t + l, i});
            }
            Collections.sort(events, (Comparator.comparingInt(o -> o[0])));
            Map<Integer, Integer> m = new TreeMap<>();
            for(int[] e: events) {
                Integer[] a = m.keySet().toArray(new Integer[0]);
                int i = binsearch(0, m.size()-1, e[0], a);
                if(i==-1){
                    m.compute(e[1], (key, value)->(value==null)?1:value+1);
                } else {
                    int v = m.get(a[i]);
                    if(v>1){
                        m.put(a[i], v-1);
                    } else {
                        m.remove(a[i]);
                    }
                    m.compute(e[1], (key, value)->(value==null)?1:value+1);
                }
            }
            writer.println(m.values().stream().reduce(0, Integer::sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
