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
}