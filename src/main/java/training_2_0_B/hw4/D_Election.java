package training_2_0_B.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class D_Election {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            Map<String, Long> map = new HashMap<>();
            List<String> parties = new ArrayList<>();
            String input;
            long total = 0;
            while ((input = reader.readLine()) != null) {
                int space = input.trim().lastIndexOf(" ");
                String party = input.substring(0, space);
                parties.add(party);
                long count = Long.parseLong(input.substring(space + 1));
                map.put(party, count);
                total += count;
            }
            double pich = (double) total / 450;
            Map<String, Long> res = new HashMap<>();
            long total1 = 0;
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                total1 += (long) (entry.getValue() / pich);
                res.put(entry.getKey(), (long) (entry.getValue() / pich));

            }
            List<Object[]> listSort = new ArrayList<>();
            if (total1 < 450) {
                for (Map.Entry<String, Long> entry : map.entrySet()) {
                    listSort.add(new Object[]{entry.getValue()/pich - (long) (entry.getValue() / pich), entry.getKey()});
                }
                listSort.sort(((o1, o2) -> (int) ((double)o2[0]-(double)o1[0])));
                long points = total1-450;
                for(int i = 0; i<listSort.size(); i++){

                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
