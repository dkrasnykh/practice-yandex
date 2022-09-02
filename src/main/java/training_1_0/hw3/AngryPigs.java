package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class AngryPigs {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            Set<Integer> x = new HashSet<>();

            for (int i = 0; i < n; i++) {
                String[] xy = reader.readLine().split(" ");
                x.add(Integer.parseInt(xy[0]));
            }

            System.out.println(x.size());
        }
    }
}
