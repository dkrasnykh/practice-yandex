package training_1_0.hw7.cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

class Event implements Comparable {
    private int time;
    private int type;
    private int number;
    public Event(int time, int type, int number) {
        this.time = time;
        this.type = type;
        this.number = number;
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
        return (this.time == other.time) ? other.type - this.type : this.time - other.time;
    }
}

public class Cash {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            List<Event> event = new ArrayList<>(n * 2);
            String[] line;
            for (int i = 0; i < n; i++) {
                line = regex.split(reader.readLine());
                event.add(new Event(Integer.parseInt(line[0]) * 60 + Integer.parseInt(line[1]), -1, i));
                event.add(new Event(Integer.parseInt(line[2]) * 60 + Integer.parseInt(line[3]), 1, i));
            }
            Collections.sort(event);
            Set<Integer> open = new HashSet<>();
            for (Event e : event) {
                if (e.getType() == -1) {
                    open.add(e.getNumber());
                } else if (e.getType() == 1) {
                    open.remove(e.getNumber());
                }
            }
            int openCashCnt = open.size();
            int openMinutes = 0;
            int last = 0;
            for (Event e : event) {
                if (e.getType() == -1) {
                    openCashCnt++;
                    if (openCashCnt == n) {
                        last = e.getTime();
                    }
                } else if (e.getType() == 1) {
                    if (openCashCnt == n) {
                        openMinutes += e.getTime() - last;
                    }
                    openCashCnt--;
                }
            }
            if (openCashCnt == n) {
                openMinutes += 24 * 60 - last;
            }
            writer.println(openMinutes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
