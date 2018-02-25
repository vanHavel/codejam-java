package utility;

import algorithm.Combinatorics;
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

}