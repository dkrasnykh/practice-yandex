package training_1_0.hw7.contemporaries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

class Event implements Comparable {
    private long time;
    private int type;
    private int number;

    public Event(long time, int type, int number) {
        this.time = time;
        this.type = type;
        this.number = number;
    }

    public long getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return time == event.time && type == event.type && number == event.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, number);
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;

        return (this.time == other.time) ? other.type - this.type : Long.compare(this.time, other.time);
    }
}

public class Contemporaries {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            List<Event> events = new ArrayList<>();
            String[] line;
            int year1;
            int month1;
            int day1;
            int year2;
            int month2;
            int day2;
            for (int i = 0; i < n; i++) {
                line = regex.split(reader.readLine());
                day1 = Integer.parseInt(line[0]);
                month1 = Integer.parseInt(line[1]);
                year1 = Integer.parseInt(line[2]);
                day2 = Integer.parseInt(line[3]);
                month2 = Integer.parseInt(line[4]);
                year2 = Integer.parseInt(line[5]);

                long eighteenth = LocalDateTime.of(year1 + 18, month1, day1, 0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                long eightieth = LocalDateTime.of(year1 + 80, month1, day1, 0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                long dod = LocalDateTime.of(year2, month2, day2, 0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

                if (eighteenth >= dod) {
                    continue;
                }

                events.add(new Event(eighteenth, -1, i + 1));
                events.add(new Event(Math.min(eightieth, dod), 1, i + 1));
            }
            Collections.sort(events);

            if (events.size() == 0) {
                writer.println("0");
                return;
            }
            Set<Integer> open = new HashSet<>();
            Set<Integer> max = new TreeSet<>();
            for (Event e : events) {
                if (e.getType() == -1) {
                    open.add(e.getNumber());
                } else if (e.getType() == 1) {
                    if (!max.containsAll(open)) {
                        max = new TreeSet<>(open);
                        String s = max.toString();
                        writer.println(s.substring(1, s.length() - 1).replaceAll(",", ""));
                    }
                    open.remove(e.getNumber());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
