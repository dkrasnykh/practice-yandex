package training_1_0.hw7.building;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

class Event {
    private int number;
    private int time;
    private int type;
    private int area;

    public int getNumber() {
        return number;
    }

    public int getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public int getArea() {
        return area;
    }

    public Event(int number, int time, int type, int area) {
        this.number = number;
        this.time = time;
        this.type = type;
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return number == event.number && time == event.time && type == event.type && area == event.area;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, time, type, area);
    }
}

public class Building {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {

            String[] line = reader.readLine().trim().split(" ");
            int N = Integer.parseInt(line[0]);
            int W = Integer.parseInt(line[1]);
            int L = Integer.parseInt(line[2]);
            int totalArea = W * L;

            List<Event> events = new ArrayList<>();
            int x1, y1, z1, x2, y2, z2;
            for (int i = 0; i < N; i++) {
                line = reader.readLine().trim().split(" ");
                x1 = Integer.parseInt(line[0]);
                y1 = Integer.parseInt(line[1]);
                z1 = Integer.parseInt(line[2]);
                x2 = Integer.parseInt(line[3]);
                y2 = Integer.parseInt(line[4]);
                z2 = Integer.parseInt(line[5]);
                events.add(new Event(i + 1, z1, 1, Math.abs(x2 - x1) * Math.abs(y2 - y1)));
                events.add(new Event(i + 1, z2, -1, Math.abs(x2 - x1) * Math.abs(y2 - y1)));
            }

            Collections.sort(events, (e1, e2) -> e1.getTime() == e2.getTime() ? e1.getType() - e2.getType() : e1.getTime() - e2.getTime());

            int occupied = 0;
            int block = 0;
            int min = N + 1;
            for (Event e : events) {
                if (e.getType() == -1) {
                    occupied -= e.getArea();
                    block -= 1;
                } else if (e.getType() == 1) {
                    occupied += e.getArea();
                    block += 1;
                }
                if (occupied == totalArea) {
                    min = Math.min(min, block);
                }
            }
            if (min == N + 1) {
                writer.println("NO");
                return;
            }
            writer.println("YES");
            writer.println(min);
            Set<Integer> open = new HashSet<>();
            for (Event e : events) {
                if (e.getType() == -1) {
                    open.remove(e.getNumber());
                    occupied -= e.getArea();
                    block -= 1;
                } else if (e.getType() == 1) {
                    open.add(e.getNumber());
                    occupied += e.getArea();
                    block += 1;
                }
                if (occupied == totalArea && block == min) {
                    writer.println(open.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
