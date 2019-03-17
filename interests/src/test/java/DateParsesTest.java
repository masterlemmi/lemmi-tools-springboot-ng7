import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}
