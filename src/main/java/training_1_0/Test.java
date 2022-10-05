package training_1_0;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        System.out.println(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
        System.out.println(LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)));
        //System.out.println(new LocalDate().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
        System.out.println(TemporalAdjusters.next(DayOfWeek.TUESDAY).adjustInto(LocalDate.now()));
        //System.out.println(new LocalDate().adjust(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
    }
}
