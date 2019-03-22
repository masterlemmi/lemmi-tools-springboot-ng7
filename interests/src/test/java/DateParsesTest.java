import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateParsesTest {

    @Test
    public void initialDay(){
        LocalDate initial = LocalDate.of(2014, 2, 13);
        LocalDate start = initial.withDayOfMonth(3);
        System.out.println(start);
    }

    @Test
    public void parse(){
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yy");
        String dt = "2/27/29";

        LocalDate date = LocalDate.parse(dt, dtf);
        System.out.println(date);

    }

    @Test
    public void testIterateDate(){

        LocalDate fromRange = LocalDate.of(2019, 10, 1);
        LocalDate toRange = LocalDate.of(2020, 12, 31);

        LocalDate end = toRange.withDayOfMonth(1);
        for (LocalDate start = fromRange.withDayOfMonth(1);
             start.isBefore(end) || start.isEqual(end);
             start = start.with(TemporalAdjusters.firstDayOfNextMonth())) {

            System.out.println(start);

            }

        }

}
