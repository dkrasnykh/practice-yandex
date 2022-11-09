package training_2_0_B.hw7.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class B_Customs1 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());

            List<int[]> events = new ArrayList<>(n);
            Set<Integer> set = new TreeSet<>();

            String[] line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine().split(" ");
                int t = Integer.parseInt(line[0]);
                int l = Integer.parseInt(line[1]);
                events.add(new int[]{t, t + l, i});
            }
            Collections.sort(events, (Comparator.comparingInt(o -> o[0])));
            List<Integer> ans = new ArrayList<>();
            Set<Integer> tree = new TreeSet<>();
            //List<Integer> a =
            Map<Integer, Integer> m = new HashMap<>();

            for(int[] e: events) {

                Optional<Integer> opt = ans.stream().filter(i->i<=e[0]).findFirst();

                if(opt.isPresent()){
                    int i = ans.indexOf(opt.get());
                    ans.set(i, e[1]);
                } else {
                    ans.add(e[1]);
                }
            }
            writer.println(ans.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
