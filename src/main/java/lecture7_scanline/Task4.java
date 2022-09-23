package lecture7_scanline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
36:45
На парковке в торговом центре N мест (занумерованных от 1 до N). За день в ТЦ приезжало M автомобилей, при этом некоторые из них длинные и занимали несколько подряд идущих парковочных мест. Для каждого автомобиля известно время приезда и отъездаб а так же два числа - с какого по какое парковочное место он занимал. Если в какой-то момент автомобиль уехал с парковочного места, то место считается освободившимся и в тот же момент времени на его место может встать другой.

Необходимо определить, был ли момент, в который были заняты все парковочные места.

События - приезд и отъезд автомобиля (причем отъезд должен происходить раньше). Будем поддерживать количество занятых мест и если после очередного события счетчик равен N, то такие моменты были
 */
class CarEvent implements Comparable {
    private int time;
    private int type;
    private int seats;

    public CarEvent(int time, int type, int seats) {
        this.time = time;
        this.type = type;
        this.seats = seats;
    }

    public int getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public int compareTo(Object o) {
        CarEvent other = (CarEvent) o;
        return (this.time == other.time) ? this.type - other.type : this.time - other.time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEvent carEvent = (CarEvent) o;
        return time == carEvent.time && type == carEvent.type && seats == carEvent.seats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, seats);
    }
}

public class Task4 {
    static boolean isParkingFull(int n, int[] tin, int[] tout, int[] placefrom, int[] placeto) {
        List<CarEvent> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.add(new CarEvent(tin[i], 1, placeto[i] - placefrom[i] + 1));
            events.add(new CarEvent(tout[i], -1, placeto[i] - placefrom[i] + 1));
        }
        Collections.sort(events);
        int occupied = 0;
        for (CarEvent e : events) {
            if (e.getType() == -1) {
                occupied -= e.getSeats();
            } else if (e.getType() == 1) {
                occupied += e.getSeats();
            }
            if(occupied == n){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
