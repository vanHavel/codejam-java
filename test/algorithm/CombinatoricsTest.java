package algorithm;

import data.Tuple;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        // large numbers
        assertEquals(10, Combinatorics.nChooseK(1000, 483, 13));
        assertEquals(0, Combinatorics.nChooseK(10000, 4832, 13));
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

    @Test
    void testFastNChooseK() {
        // tests like for combinations
        assertEquals(1, Combinatorics.nChooseKModuloPrime(0,0,13));
        assertEquals(2, Combinatorics.nChooseKModuloPrime(2,1,13));
        assertEquals(10, Combinatorics.nChooseKModuloPrime(5,3,13));
        assertEquals(6, Combinatorics.nChooseKModuloPrime(10,2,13));
        assertEquals(7, Combinatorics.nChooseKModuloPrime(6,3,13));
        assertEquals(1, Combinatorics.nChooseKModuloPrime(10,10,13));
        // large numbers
        assertEquals(10, Combinatorics.nChooseKModuloPrime(1000,483,13));
        assertEquals(0, Combinatorics.nChooseKModuloPrime(10000,4832,13));
    }

    @Test
    void testPermutations() {
        // test empty set
        Set<Integer> empty = new HashSet<>();
        Vector<Vector<Integer>> emptyPerms = Combinatorics.permutations(empty);
        Vector<Vector<Integer>> expected = new Vector<>();
        expected.add(new Vector<>());
        assertEquals(expected, emptyPerms);

        // test 2 element set
        Set<Integer> two = new HashSet<>();
        two.add(1);
        two.add(2);
        Vector<Vector<Integer>> twoPerms = Combinatorics.permutations(two);
        Vector<Vector<Integer>> twoExpected = new Vector<>();
        Vector<Integer> onetwo = new Vector<>();
        Vector<Integer> twoone = new Vector<>();
        onetwo.add(1);
        onetwo.add(2);
        twoone.add(2);
        twoone.add(1);
        twoExpected.add(onetwo);
        twoExpected.add(twoone);
        assertEquals(new HashSet<>(twoExpected), new HashSet<>(twoPerms));
    }

    @Test
    void testPermutationsMultiset() {
        // test empty sequence
        Vector<Integer> empty = new Vector<>();
        Set<Vector<Integer>> emptyPerms = Combinatorics.permutationsMultiset(empty);
        Set<Vector<Integer>> expected = new HashSet<>();
        expected.add(new Vector<>());
        assertEquals(expected, emptyPerms);

        // test 2 element sequence with distinct elements
        Vector<Integer> onetwo = new Vector<>();
        onetwo.add(1);
        onetwo.add(2);
        Set<Vector<Integer>> twoPerms = Combinatorics.permutationsMultiset(onetwo);
        Set<Vector<Integer>> twoExpected = new HashSet<>();
        Vector<Integer> twoone = new Vector<>();
        twoone.add(2);
        twoone.add(1);
        twoExpected.add(onetwo);
        twoExpected.add(twoone);
        assertEquals(twoExpected, twoPerms);

        // test 2 element sequence with identical elements
        Vector<Integer> oneone = new Vector<>();
        oneone.add(1);
        oneone.add(1);
        Set<Vector<Integer>> onePerms = Combinatorics.permutationsMultiset(oneone);
        Set<Vector<Integer>> oneExpected = new HashSet<>();
        oneExpected.add(oneone);
        assertEquals(oneExpected, onePerms);
    }
}