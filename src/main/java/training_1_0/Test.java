package training_1_0;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        List s1 = new ArrayList( );
        s1.add("a");
        s1.add("b");
        s1.add(1, "c");
        List s2 = new ArrayList(  s1.subList(1, 1) );
        s1.addAll(s2);
        System.out.println(s1);


        StringBuilder sb = new StringBuilder();

        System.out.println(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
        System.out.println(LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)));
        //System.out.println(new LocalDate().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
        System.out.println(TemporalAdjusters.next(DayOfWeek.TUESDAY).adjustInto(LocalDate.now()));
        //System.out.println(new LocalDate().adjust(TemporalAdjusters.next(DayOfWeek.TUESDAY)));

        String str = null;
        str+="good";
        System.out.println(str);

        //StringBuilder sb = new StringBuilder("")
        SecurityException es;
    }

    @Override
    public String toString() {
        return "Test{}";
    }
}
