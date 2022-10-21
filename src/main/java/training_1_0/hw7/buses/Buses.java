package training_1_0.hw7.buses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class Event implements Comparable {
    private int number;
    private int from;
    private int to;
    private int time;
    private int type;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public Event(int number, int from, int to, int time, int type) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.type = type;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return number == event.number && from == event.from && to == event.to && time == event.time && type == event.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, from, to, time, type);
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return (this.time == other.time) ? other.type - this.type : this.time - other.time;
    }
}

// https://contest.yandex.ru/contest/27883/problems/I/
public class Buses {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            //автобус с индексом 0 - автобус в пути
            int[] b = new int[n + 1];
            List<Event> events = new ArrayList<>(m * 2);
            // -1 - начало события
            // 1 - конец события
            int time;
            int from;
            int to;
            for (int i = 0; i < m; i++) {
                line = reader.readLine().trim().split(" ");
                time = Integer.parseInt(line[1].substring(0, 2)) * 60 + Integer.parseInt(line[1].substring(3));
                from = Integer.parseInt(line[0]);
                to = Integer.parseInt(line[2]);
                events.add(new Event(i, from, to, time, -1));
                time = Integer.parseInt(line[3].substring(0, 2)) * 60 + Integer.parseInt(line[3].substring(3));
                events.add(new Event(i, from, to, time, 1));
            }

            Collections.sort(events);
            int cnt = 0;
            Set<Integer> open = new HashSet<>();
            for (int i = 0; i < 2; i++) {
                for (Event e : events) {
                    if (e.getType() == -1) {
                        //если нет свободного автобуса в городе from
                        open.add(e.getNumber());
                        b[0] += 1;
                        if (b[e.getFrom()] == 0) {
                            cnt++;
                        } else {
                            b[e.getFrom()] -= 1;
                        }
                    } else if (e.getType() == 1) {
                        if (open.contains(e.getNumber())) {
                            b[0] -= 1; // автобус в пути
                            b[e.getTo()] += 1;
                            open.remove(e.getNumber());
                        }
                    }
                }
            }
            int ans = cnt;

            for (Event e : events) {
                if (e.getType() == -1) {
                    //если нет свободного автобуса в городе from
                    open.add(e.getNumber());
                    b[0] += 1;
                    if (b[e.getFrom()] == 0) {
                        cnt++;
                    } else {
                        b[e.getFrom()] -= 1;
                    }
                } else if (e.getType() == 1) {
                    if (open.contains(e.getNumber())) {
                        b[0] -= 1; // автобус в пути
                        b[e.getTo()] += 1;
                        open.remove(e.getNumber());
                    }
                }
            }

            if (ans != cnt) {
                writer.println("-1");
            } else {
                writer.println(ans);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
