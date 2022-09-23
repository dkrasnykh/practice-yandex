package lecture7_scanline;

import java.util.*;

/*
04:20
Сайт посетило N человек, для кажджого известно время входа на сайт In(i) и время выхода с сайта Out(i). Считается, что человек был на сайте с момента In(i) и Out(i) включительно.

Определите, какое максимальное количество человек было на сайте одновременно.
 */
class Event implements Comparator, Comparable {
    private int time;
    private int type;

    public Event(int time, int type) {
        this.time = time;
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Event e1 = (Event) o1;
        Event e2 = (Event) o2;
        return (e1.time == e2.time) ? e1.type - e2.type : e1.time - e2.time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return time == event.time && type == event.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type);
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return (this.time == other.time) ? this.type - other.type : this.time - other.time;
    }
}

public class Task1 {
    static int maxVisitorsOnline(int n, int[] tin, int[] tout) {
        /*
        -1 - приход
        1 - выход
         */
        List<Event> events = new ArrayList<>(n * 2);
        for (int i = 0; i < n; i++) {
            events.add(new Event(tin[i], -1));
            events.add(new Event(tout[i], 1));
        }
        Collections.sort(events);
        int online = 0;
        int maxOnline = 0;
        for (Event e : events) {
            if (e.getType() == -1) {
                online += 1;
            } else {
                online -= 1;
            }
            maxOnline = Math.max(online, maxOnline);
        }
        return maxOnline;
    }

    public static void main(String[] args) {


    }
}
