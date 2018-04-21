package algorithm;

import data.Pair;
import data.Tuple;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerRingsTest {

    @Test
    public void testEEA() {
        Tuple<Long, Pair<Long>> res1 = IntegerRings.extendedEuclidean(5, 10);
        assertEquals(5, (long) res1.fst);
        assertEquals(1, (long) res1.snd.fst % 10);
        assertEquals(0, (long) res1.snd.snd % 10);

        Tuple<Long, Pair<Long>> res2 = IntegerRings.extendedEuclidean(13, 38);
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

    @Test
    public void testCrt() {
        List<Pair<Long>> constraints = new LinkedList<>();
        constraints.add(new Pair<>(0L,3L));
        Pair<Long> solution = IntegerRings.chineseRemainderTheorem(constraints);
        assertEquals(0L, (long) solution.fst);
        assertEquals(3L, (long) solution.snd);

        constraints.add(new Pair<>(3L,4L));
        solution = IntegerRings.chineseRemainderTheorem(constraints);
        assertEquals(3L, (long) solution.fst);
        assertEquals(12L, (long) solution.snd);

        constraints.add(new Pair<>(4L,5L));
        solution = IntegerRings.chineseRemainderTheorem(constraints);
        assertEquals(39L, (long) solution.fst);
        assertEquals(60L, (long) solution.snd);
    }
}