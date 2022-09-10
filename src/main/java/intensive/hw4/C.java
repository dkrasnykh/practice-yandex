package intensive.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

class Event implements Comparable {
    private int time;
    private int type;

    public int getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public Event(int time, int type) {
        this.time = time;
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return (this.time == other.time) ? this.type - other.type : this.time - other.time;
    }
}

public class C {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            String[] lnm = regex.split(reader.readLine());
            int l = Integer.parseInt(lnm[0]);
            int n = Integer.parseInt(lnm[1]);
            int m = Integer.parseInt(lnm[2]);
            List<Event> events = new ArrayList<>(n * 2 + m);
            String[] input;
            for (int i = 0; i < n; i++) {
                input = regex.split(reader.readLine());
                events.add(new Event(Integer.parseInt(input[0]), -1));
                events.add(new Event(Integer.parseInt(input[1]), 1));
            }

            int[] checkPos = new int[m];
            for (int i = 0; i < m; i++) {
                checkPos[i] = Integer.parseInt(reader.readLine());
                events.add(new Event(checkPos[i], 0));
            }
            Collections.sort(events);
            int[] t = new int[l+1];
            int covering = 0;
            for (Event s : events) {
                if (s.getType() == -1) {
                    covering++;
                } else if (s.getType() == 1) {
                    covering--;
                } else {
                    t[s.getTime()] = covering;
                }
            }
            for (int i = 0; i < checkPos.length; i++) {
                System.out.println(t[checkPos[i]]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
