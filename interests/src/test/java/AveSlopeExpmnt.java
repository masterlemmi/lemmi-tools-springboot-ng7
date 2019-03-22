import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AveSlopeExpmnt {

    //no such thing as ave slope.. it's just the same as first point to last point.. :-/

    float[][] consistentPaymentOf50 = {
        {1, 1000},
        {2, 950},
        {3, 900},
        {4, 850},
        {5, 800},
    };



    float[][] neverPaying = {
        {1, 1000},
        {2, 1023},
        {3, 1056},
        {4, 1116},
        {5, 1226},
    };
    float[][] incosistent = {
        {1, 1000f},
        {2, 1100f},
        {3, 1230f},
        {4, 1000f},
        {5, 1200f},
        {6, 1300f},
        {7, 1200f},
        {8, 1020f},
        {9, 1240f},
        {10, 1330f},
        {11, 1500f},
        {12, 800f},
        {13, 1300f},
        {14, 900f},
        {15, 1100f},
        {16, 100230f},
    };


    @Test
    public void testPayingInconsistently(){
        float ave = getAveSlope(incosistent);
        assertTrue (ave > 0);
    }

    @Test
    public void testNeverPaying(){
        float ave = getAveSlope(neverPaying);
        assertTrue (ave > 0);
    }

    @Test
    public void testConsistentPayment(){
        float ave = getAveSlope(consistentPaymentOf50);
        assertTrue (ave < 0);
    }
    private float  getAveSlope(float[][] data){
        float totalSlope = 0; System.out.print("ALL SLOPES: ");
        float len = (float) data.length;
        for (int i = 0; i< len -1; i++){

            float s = getSlope(data[i],data[i+1]);
            totalSlope +=s;
            System.out.print (s + " ");
        }
        System.out.println();
        System.out.println("TOTAL SLOPE: " + totalSlope);
        System.out.println("AVE SLOPE: " + totalSlope/(len-1));
        System.out.println("FIRST TO LAST SLOPE: " + getSlope(data[0], data[data.length-1]));

            return totalSlope/4f;

    }



    @Test
    public void getSlopeOfTwoPoints(){
        float[] pt1 = {2,15};
        float[] pt2 = {1,15};
        System.out.println(getSlope(pt1, pt2));
    }


    private float getSlope(float[] pt1, float[] pt2){
        return (pt2[1]-pt1[1]) / (pt2[0]-pt1[0]);
    }
}
