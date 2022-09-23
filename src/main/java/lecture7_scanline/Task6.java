package lecture7_scanline;

import java.util.*;

class CarEvent1 implements Comparable {
    private int time;
    private int type;
    private int seats;
    private int number;

    public CarEvent1(int time, int type, int seats, int number) {
        this.time = time;
        this.type = type;
        this.seats = seats;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEvent1 carEvent1 = (CarEvent1) o;
        return time == carEvent1.time && type == carEvent1.type && seats == carEvent1.seats && number == carEvent1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, seats, number);
    }

    @Override
    public int compareTo(Object o) {
        CarEvent1 other = (CarEvent1) o;
        return (this.time == other.time) ? this.type - other.type : this.time - other.time;
    }
}

/*
45:51 (53:26 - эффективное решение)
На парковке в торговом центре N мест (занумерованных от 1 до N). За день в ТЦ приезжало M автомобилей, при этом некоторые из них длинные и занимали несколько подряд идущих парковочных мест. Для каждого автомобиля известно время приезда и отъездаб а так же два числа - с какого по какое парковочное место он занимал. Если в какой-то момент автомобиль уехал с парковочного места, то место считается освободившимся и в тот же момент времени на его место может встать другой.

Необходимо определить, был ли момент, в который были заняты все парковочные места и определить минимальное количество автомобилей, которое заняло все места, а так же номера этих автомобилей (в том порядке, как они перечисляются в списке). Если парковка никогда не была занята полностью - вернуть пустой список.
*/
public class Task6 {
    static Set<Integer> minCarsOnFullParking(int n, int[] tin, int[] tout, int[] placefrom, int[] placeto, int[] number) {
        List<CarEvent1> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.add(new CarEvent1(tin[i], 1, placeto[i] - placefrom[i] + 1, i));
            events.add(new CarEvent1(tout[i], -1, placeto[i] - placefrom[i] + 1, i));
        }
        Collections.sort(events);
        int occupied = 0;
        int nowCars = 0;
        int minCars = n + 1;
        for (CarEvent1 e : events) {
            if (e.getType() == -1) {
                occupied -= e.getSeats();
                nowCars--;
            } else if (e.getType() == 1) {
                occupied += e.getSeats();
                nowCars++;
            }
            if (occupied == n && nowCars < minCars) {
                minCars = nowCars;
            }
        }
        Set<Integer> carnums = new HashSet<>();
        nowCars = 0;
        for (CarEvent1 e : events) {
            if(e.getType()==-1){
                occupied -= e.getSeats();
                nowCars--;
                carnums.add(e.getNumber());
            } else if (e.getType()==1){
                occupied+=e.getSeats();
                nowCars++;
                carnums.add(e.getNumber());
            }
            if(occupied==n && nowCars==minCars){
                return carnums;
            }
        }
        return Collections.EMPTY_SET;
    }

    public static void main(String[] args) {

    }
}
