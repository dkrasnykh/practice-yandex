package training_1_0.hw7.student_observation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class Event implements Comparable {
    private int position;
    private int type;

    public Event(int position, int type) {
        this.position = position;
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return position == event.position && type == event.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, type);
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return (this.position == other.position) ? this.type - other.type : this.position - other.position;
    }
}

public class StudentObservation {
    static int studentsWithoutObservation(int n, List<Event> events) {
        Collections.sort(events);
        int cnt = 0;
        int observed = 0;
        int start = 0;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getType() == -1) {
                if (cnt == 0) {
                    start = events.get(i).getPosition();
                }
                cnt++;
            } else {
                if (cnt == 1) {
                    observed += events.get(i).getPosition() - start + 1;
                }
                cnt--;
            }
        }
        return n - observed;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String line = reader.readLine();
            int space = line.indexOf(' ');
            int n = Integer.parseInt(line.substring(0, space));
            int m = Integer.parseInt(line.substring(space + 1));
            List<Event> events = new ArrayList<>(2 * m);
            for (int i = 0; i < m; i++) {
                line = reader.readLine();
                space = line.indexOf(' ');
                events.add(new Event(Integer.parseInt(line.substring(0, space)), -1));
                events.add(new Event(Integer.parseInt(line.substring(space + 1)), 1));
            }
            writer.println(studentsWithoutObservation(n, events));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
