package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Maya2 {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] input = regex.split(reader.readLine());
            int gl = Integer.parseInt(input[0]);
            int sl = Integer.parseInt(input[1]);
            String g = reader.readLine();
            String s = reader.readLine();
            char[] chg = g.toCharArray();
            char[] chs = s.toCharArray();
            Map<Character, Long> gmap = new HashMap<>(gl);
            for (int i = 0; i < chg.length; i++) {
                gmap.compute(chg[i], (key, total) -> (total == null) ? 1 : total + 1);
            }

            int cnt = 0;
            Map<Character, Long> smap;
            for (int i = 0; i < s.length() - g.length() + 1; i++) {
                smap = new HashMap<>(gl);
                for (int j = 0; j < gl; j++) {
                    smap.compute(chs[i+j], (key, total) -> (total == null) ? 1 : total + 1);
                }
                if(smap.entrySet().stream().allMatch(e-> e.getValue().equals(gmap.get(e.getKey())))){
                    cnt++;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
