package intensive.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

class EventB implements Comparable {
    private int start;
    private int finish;

    public int getStart() {
        return start;
    }

    public int getFinish() {
        return finish;
    }

    public EventB(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    @Override
    public int compareTo(Object o) {
        EventB other = (EventB) o;
        return this.finish - other.finish;
    }
}

public class B {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            List<EventB> events = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] input = regex.split(reader.readLine());
                events.add(new EventB(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
            }
            Collections.sort(events);
            int count = 0;
            int finish = -1;
            for (EventB e : events) {
                if (finish <= e.getStart()) {
                    finish = e.getFinish();
                    count++;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
