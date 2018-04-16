package utility;

import algorithm.Combinatorics;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

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
    void testIntegerPower() {
        assertEquals(1024, MyUtility.integerPower(2, 10));
        assertEquals(81, MyUtility.integerPower(3, 4));
        assertEquals(125, MyUtility.integerPower(5, 3));
    }

    @Test
    void testBinarySearch() {
        int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Predicate<Long> test = i -> arr[i.intValue()] >= 5 && arr[i.intValue()] <= 10;
        assertEquals(4, MyUtility.binarySearchFirst(0,14, test));
        assertEquals(9, MyUtility.binarySearchLast(0,14, test));
    }

}