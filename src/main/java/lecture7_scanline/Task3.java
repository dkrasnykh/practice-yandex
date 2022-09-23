package lecture7_scanline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
18:17
Сайт посетило N человек, для кажджого известно время входа на сайт In(i) и время выхода с сайта Out(i). Считается, что человек был на сайте с момента In(i) и Out(i) включительно. Начальник заходил на сайт M раз в моменты времени Boss(i) и смотрел, сколько людей сейчас онлайн. Посещения сайта начальников упорядочены по возрастанию времени.

Определите, какие показания счетчика людей онлайн увидел начальник.
 */
public class Task3 {
    static List<Integer> bossCounters(int n, int[] tin, int[] tout, int m, int[] tboss) {
        List<Event> events = new ArrayList<>(2 * n + m);
        for (int i = 0; i < n; i++) {
            events.add(new Event(tin[i], -1));
            events.add(new Event(tout[i], 1));
        }
        for (int i = 0; i < m; i++) {
            events.add(new Event(tboss[i], 0));
        }
        Collections.sort(events);
        int online = 0;
        List<Integer> bossans = new ArrayList<>(m);
        for (Event e : events) {
            if (e.getType() == -1) {
                online++;
            } else if (e.getType() == 1) {
                online--;
            } else {
                bossans.add(online);
            }
        }
        return bossans;
    }

    public static void main(String[] args) {

    }
}
