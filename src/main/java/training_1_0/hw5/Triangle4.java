package training_1_0.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class Pair{
    int x;
    int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Triangle4 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int[][] points = new int[n][2];
            String line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                int space = line.indexOf(' ');
                points[i][0] = Integer.parseInt(line.substring(0, space));
                points[i][1] = Integer.parseInt(line.substring(space + 1));
            }
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                Set<Pair> usedvectors = new HashSet<>();
                List<Long> neig = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    int vecx = points[i][0] - points[j][0];
                    int vecy = points[i][1] - points[j][1];
                    long veclen = ((long) vecx)*((long) vecx) + ((long) vecy)*((long) vecy);
                    neig.add(veclen);
                    if (usedvectors.contains(new Pair(vecx, vecy))) {
                        cnt--;
                    }
                    usedvectors.add(new Pair(-vecx, -vecy));
                }
                Collections.sort(neig);
                int r = 0;
                for (int l = 0; l < neig.size(); l++) {
                    while (r < neig.size() && neig.get(l).equals(neig.get(r))) {
                        r++;
                    }
                    cnt += r - l - 1;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
