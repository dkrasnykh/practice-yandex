package training_1_0.hw7.points_and_lines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

class Point implements Comparable {
    private int x;
    private int type;

    public Point(int position, int type) {
        this.x = position;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        Point other = (Point) o;
        return (this.x == other.x) ? this.type - other.type : this.x - other.x;
    }
}

public class PointsAndLines {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine();
            int space = line.indexOf(' ');
            int n = Integer.parseInt(line.substring(0, space));
            int m = Integer.parseInt(line.substring(space + 1));
            List<Point> points = new ArrayList<>(2 * n + m);
            int a, b;
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                space = line.indexOf(' ');
                a = Integer.parseInt(line.substring(0, space));
                b = Integer.parseInt(line.substring(space + 1));
                points.add(new Point(Math.min(a, b), -1));
                points.add(new Point(Math.max(a, b), 1));
            }

            int[] p = new int[m];
            String[] line1 = regex.split(reader.readLine());
            for (int i = 0; i < m; i++) {
                a = Integer.parseInt(line1[i]);
                points.add(new Point(a, 0));
                p[i] = a;
            }
            Collections.sort(points);
            Map<Integer, Integer> ans = new HashMap<>();
            int cnt = 0;
            for (Point point : points) {
                if (point.getType() == -1) {
                    cnt++;
                } else if (point.getType() == 1) {
                    cnt--;
                } else {
                    int finalCnt = cnt;
                    ans.compute(point.getX(), (key, value) -> finalCnt);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < p.length - 1; i++) {
                sb.append(ans.get(p[i])).append(" ");
            }
            sb.append(ans.get(p[p.length - 1]));
            writer.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
