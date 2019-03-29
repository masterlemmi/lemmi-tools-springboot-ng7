import org.junit.Test;

import java.time.LocalDate;

public class StringFormatTest {
    @Test
    public void testFormat(){
        Double aveRise = -10d;
        String msg = String.format("There is %s trend of %.2f per month.",
            aveRise > 0 ? "an upwards" : "a downwards", aveRise);
        System.out.println(msg);
    }

    @Test
    public void testFormat2(){
        String calcNotes =  " With a consistent payment of  %.2f per month and an interest rate of  %.2f%%"
            + " you are expected to pay off your %s debt in  %d months! Thats  %s!";

        double payment = 3000d;
        double interest = 3.5;
        int paymentCounts = 10;
        String uiName = "BPI";
        LocalDate estimateFinish = LocalDate.now();

        System.out.println(String.format(calcNotes, payment, interest, uiName,  paymentCounts, estimateFinish));

    }

    private String identifyDuration(int months){

        if (months > 11){
            return months/12 + " years and " + months%12 + " months";
        } else {
            return months + " months";
        }
    }
}
