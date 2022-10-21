package training_1_0.hw7.advertising2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

class Event implements Comparable {
    private int time;
    private int type;

    private int number;
    private int a;
    private int b;

    public Event(int time, int type, int number, int a, int b) {
        this.time = time;
        this.type = type;
        this.number = number;
        this.a = a;
        this.b = b;
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

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return time == event.time && type == event.type && number == event.number && a == event.a && b == event.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, number, a, b);
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return (this.time == other.time) ? this.type - other.type : this.time - other.time;
    }
}

//TL
public class Advertising {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {

            int a, b, n = Integer.parseInt(reader.readLine());
            String[] line;

            List<Event> event = new ArrayList<>(n * 2);
            for (int i = 0; i < n; i++) {
                line = reader.readLine().trim().split(" ");
                a = Integer.parseInt(line[0]);
                b = Integer.parseInt(line[1]);
                if (b - a < 5) {
                    continue;
                }
                event.add(new Event(a, -1, i, a, b));
                event.add(new Event(b, 1, i, a, b));
            }

            Map<Integer, Set<Event>> map = new HashMap<>();
            Set<Event> inStore = new HashSet<>();

            Collections.sort(event);

            for (Event e : event) {
                if (e.getType() == -1) {
                    inStore.add(e);
                } else if (e.getType() == 1) {
                    Set<Event> eventSet = map.get(e.getTime() - 5);
                    if (eventSet == null) {
                        eventSet = new HashSet<>();
                    }
                    eventSet.addAll(inStore.stream().filter(e1 -> e1.getA() <= e.getTime() - 5).collect(Collectors.toSet()));
                    map.put(e.getTime() - 5, eventSet);
                    inStore.removeIf(e1 -> e1.getNumber() == e.getNumber());
                }
            }

            int max = 0;
            int start1 = 0;
            int start2 = 0;

            for(Map.Entry entry : map.entrySet()){
                inStore = new HashSet<>();
                List<Event> eventsI = new ArrayList<>(event);
                eventsI.removeIf(e-> e.getA()<=(int)entry.getKey() && e.getB()>=(int)entry.getKey()+5);
                int localMax = 0;
                int localStart = 0;
                for(Event e : eventsI){
                    if (e.getType() == -1) {
                        inStore.add(e);
                    } else if (e.getType() == 1) {
                        int cnt1 = (int) inStore.stream().filter(e1 -> e1.getA() <= e.getTime() - 5).count();
                        if (cnt1 > localMax) {
                            localMax = cnt1;
                            localStart = e.getTime() - 5;
                        }
                        inStore.removeIf(e1 -> e1.getNumber() == e.getNumber());
                    }

                }
                if(max<((Set)entry.getValue()).size()+localMax){
                    max = ((Set)entry.getValue()).size()+localMax;
                    start1 = (int)entry.getKey();
                    start2 = localStart;
                }
            }
            int s1 = Math.min(start1, start2);
            int s2 = Math.max(start1, start2);
            if(s1==0 && s2==0){
                writer.println(max+" 1 6");
            } else if (s1==0){
                writer.println(max+" "+s2+" "+s2+5);
            } else {
                writer.println(max+" "+s1+" "+s2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
