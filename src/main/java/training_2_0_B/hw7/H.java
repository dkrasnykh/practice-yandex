package training_2_0_B.hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class H {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int k = Integer.parseInt(line[1]);
            List<String> list = new ArrayList<>();
            for(int i = 1; i<=n; i++){
                list.add(String.valueOf(i));
            }
            Collections.sort(list);
            writer.println(list.get(k-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
