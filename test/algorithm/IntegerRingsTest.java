package algorithm;

import data.Tuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerRingsTest {

    @Test
    public void testEEA() {
        Tuple<Long, Tuple<Long, Long>> res1 = IntegerRings.extendedEuclidean(5, 10);
        assertEquals(5, (long) res1.fst);
        assertEquals(1, (long) res1.snd.fst % 10);
        assertEquals(0, (long) res1.snd.snd % 10);

        Tuple<Long, Tuple<Long, Long>> res2 = IntegerRings.extendedEuclidean(13, 38);
        assertEquals(1, (long) res2.fst);
        assertEquals(3, (long) res2.snd.fst);
        assertEquals(-1, (long) res2.snd.snd);
        assertEquals(3, IntegerRings.modularInverse(13, 38));
        assertEquals(12, IntegerRings.modularInverse(38, 13));

        assertEquals(1, IntegerRings.gcd(10007, 1000000007));
    }

    @Test
    public void testExp() {
        assertEquals(1024, IntegerRings.modularExponentiation(2, 10, 1025));
        assertEquals(81, IntegerRings.modularExponentiation(3,4,202));
        assertEquals(125 % 7, IntegerRings.modularExponentiation(5,3,7));
        assertEquals(1, IntegerRings.modularExponentiation(434,0,66445));
    }
}