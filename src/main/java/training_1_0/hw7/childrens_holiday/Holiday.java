package training_1_0.hw7.childrens_holiday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

class Event implements Comparable {
    private int time;
    private int number;

    public Event(int time, int number) {
        this.time = time;
        this.number = number;
    }

    public int getTime() {
        return time;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return time == event.time && number == event.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, number);
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return this.time - other.time;
    }
}

public class Holiday {
    static void generateEvents(int t, int z, int y, int m, int number, List<Event> events) {
        events.add(new Event(t, number));
        int start = t;
        int cnt = 1;
        while (cnt < m) {
            if (cnt % z == 0) {
                start += y;
            }
            start += t;
            events.add(new Event(start, number));
            cnt++;
        }
    }

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine();
            int space = line.indexOf(' ');
            int m = Integer.parseInt(line.substring(0, space));
            int n = Integer.parseInt(line.substring(space + 1));

            List<Event> events = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();
            int[] t = new int[n];
            int[] z = new int[n];
            int[] y = new int[n];
            String[] line1;
            for (int i = 0; i < n; i++) {
                line1 = regex.split(reader.readLine());
                t[i] = Integer.parseInt(line1[0]);
                z[i] = Integer.parseInt(line1[1]);
                y[i] = Integer.parseInt(line1[2]);
                generateEvents(t[i], z[i], y[i], m, i, events);
            }

            if (m == 0) {
                StringBuilder sb1 = new StringBuilder();
                writer.println(0);
                for (int i = 0; i < n; i++) {
                    sb1.append("0 ");
                }
                writer.println(sb1);
                return;
            }

            Collections.sort(events);
            for (int i = 0; i < m; i++) {
                map.compute(events.get(i).getNumber(), (key, value) -> value == null ? 1 : value + 1);
            }
            writer.println(events.get(m - 1).getTime());
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < n; i++) {
                ans.append((map.get(i) == null) ? 0 : map.get(i)).append(" ");
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
