package lecture7_scanline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
43:16
На парковке в торговом центре N мест (занумерованных от 1 до N). За день в ТЦ приезжало M автомобилей, при этом некоторые из них длинные и занимали несколько подряд идущих парковочных мест. Для каждого автомобиля известно время приезда и отъездаб а так же два числа - с какого по какое парковочное место он занимал. Если в какой-то момент автомобиль уехал с парковочного места, то место считается освободившимся и в тот же момент времени на его место может встать другой.

Необходимо определить, был ли момент, в который были заняты все парковочные места и определить минимальное количество автомобилей, которое заняло все места. Если такого момента не было - вернуть M+1

Добавим ещё один счетчик на количество автомобилей и будем обновлять минимальное количество автомобилей когда заняты все места.
*/
public class Task5 {
    static int minCarOnFullParking(int n, int[] tin, int[] tout, int[] placefrom, int[] placeto) {
        List<CarEvent> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.add(new CarEvent(tin[i], 1, placeto[i] - placefrom[i] + 1));
            events.add(new CarEvent(tout[i], -1, placeto[i] - placefrom[i] + 1));
        }
        Collections.sort(events);
        int occupied = 0;
        int nowCars = 0;
        int minCars = n + 1;
        for(CarEvent e : events){
            if (e.getType()==-1) {
                occupied -= e.getSeats();
                nowCars--;
            } else if (e.getType()==1){
                occupied+=e.getSeats();
                nowCars++;
            }
            if(occupied == n){
                minCars = Math.min(minCars, nowCars);
            }
        }
        return minCars;
    }

    public static void main(String[] args) {

    }
}
