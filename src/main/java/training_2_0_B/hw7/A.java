package training_2_0_B.hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String[] line;
            List<int[]> events = new ArrayList<>(2 * n);
            for (int i = 0; i < n; i++) {
                line = reader.readLine().split(" ");
                events.add(new int[]{Integer.parseInt(line[0]), -1});
                events.add(new int[]{Integer.parseInt(line[1]), 1});
            }
            Collections.sort(events, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
            int cnt = 0;
            int lenght = 0;
            int startPosititon = 0;
            for (int[] e : events) {
                if (e[1] == -1) {
                    if(cnt==0){
                        startPosititon = e[0];
                    }
                    cnt++;
                } else if (e[1] == 1) {
                    cnt--;
                    if(cnt==0){
                        lenght+= e[0]-startPosititon;
                    }
                }
            }
            writer.println(lenght);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
