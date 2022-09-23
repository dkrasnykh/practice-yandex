package lecture7_scanline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
12:55
Сайт посетило N человек, для кажджого известно время входа на сайт In(i) и время выхода с сайта Out(i). Считается, что человек был на сайте с момента In(i) и Out(i) включительно.

Определите, какое суммарное время на сайте был хотя бы один человек.
 */
public class Task2 {
    static int timeWithVisitors(int n, int[] tin, int[] tout) {
        List<Event> events = new ArrayList<>(2 * n);
        for (int i = 0; i < n; i++) {
            events.add(new Event(tin[i], -1));
            events.add(new Event(tout[i], 1));
        }
        Collections.sort(events);
        int online = 0;
        int notEmptyTime = 0;
        for (int i = 0; i < events.size(); i++) {
            if (online > 0) {
                notEmptyTime += events.get(i).getTime() - events.get(i - 1).getTime();
            }
            if (events.get(i).getType() == -1) {
                online += 1;
            } else {
                online -= 1;
            }
        }
        return notEmptyTime;
    }

    public static void main(String[] args) {

    }
}
