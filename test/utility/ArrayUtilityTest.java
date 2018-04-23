package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilityTest {

    @Test
    public void testSum() {
        int[] ints = new int[] {1,4,8,-2};
        assertEquals(11, ArrayUtility.sum(ints));

        long[] longs = new long[] {1,4,8,-2};
        assertEquals(11, ArrayUtility.sum(longs));
    }

    @Test
    public void testMinMax() {
        long[] longs = new long[] {1,4,8,-2};
        assertEquals(-2, ArrayUtility.arrayMin(longs));
        assertEquals(8, ArrayUtility.arrayMax(longs));

    }
}