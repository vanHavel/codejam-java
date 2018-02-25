package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinatoricsTest {

    @Test
    void testNChooseK() {
        // ignoring modulus
        assertEquals(1, Combinatorics.nChooseK(0, 0, 5));
        assertEquals(2, Combinatorics.nChooseK(2, 1, 3));
        assertEquals(10, Combinatorics.nChooseK(5, 3, 11));
        // with modulus
        assertEquals(0, Combinatorics.nChooseK(5, 3, 5));
        assertEquals(6, Combinatorics.nChooseK(10, 2, 13));
    }

    @Test
    void testCombinations() {
        long[][] combinations = Combinatorics.combinations(10, 10, 13);
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