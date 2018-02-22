package Utility;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyUtilityTest {

    @Test
    void testStringAsList() {
        List<Character> s = new LinkedList<>();
        assertEquals(s, MyUtility.stringAsList(""));
        s.add('c');
        assertEquals(s, MyUtility.stringAsList("c"));
        s.add('c');
        assertEquals(s, MyUtility.stringAsList("cc"));
        s.add('a');
        assertEquals(s, MyUtility.stringAsList("cca"));
    }

    @Test
    void testNChooseK() {
        // ignoring modulus
        assertEquals(1, MyUtility.nChooseK(0, 0, 5));
        assertEquals(2, MyUtility.nChooseK(2, 1, 3));
        assertEquals(10, MyUtility.nChooseK(5, 3, 11));
        // with modulus
        assertEquals(0, MyUtility.nChooseK(5, 3, 5));
        assertEquals(6, MyUtility.nChooseK(10, 2, 13));
    }

    @Test
    void testCombinations() {
        long[][] combinations = MyUtility.combinations(10, 10, 13);
        // ignoring modulus
        assertEquals(1, combinations[0][0]);
        assertEquals(2, combinations[2][1]);
        assertEquals(10, combinations[5][3]);
        // using modulus
        assertEquals(6, combinations[10][2]);
        assertEquals(7, combinations[6][3]);
        assertEquals(1, combinations[10][10]);
    }
}