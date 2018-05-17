package algorithm;

import data.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Vector;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {

    @Test
    public void testAStarPrimes() {
        // find number of single digit prime factors of 120
        Integer initialState = 1;
        Function<Integer, Vector<Tuple<Integer, Integer>>> successors = (i -> {
            Vector<Tuple<Integer, Integer>> vec = new Vector<>(4);
            vec.add(new Tuple<>(2 * i, 1));
            vec.add(new Tuple<>(3 * i, 1));
            vec.add(new Tuple<>(5 * i, 1));
            vec.add(new Tuple<>(7 * i, 1));
            return vec;
        });
        ToIntFunction<Integer> heuristic = (i -> 0);
        Predicate<Integer> goalTest = (i -> i == 120);
        AStar aStar = new AStar(initialState, successors, goalTest, heuristic);
        aStar.search();
        assertEquals(Integer.valueOf(120), aStar.getFinalState());
        assertEquals(5, aStar.getOptimalCost());
    }

    @Test
    public void testAStarPaths() {
        // find shortest path through open field
        Tuple<Integer, Integer> initialState = new Tuple<>(0,0);
        Function<Tuple<Integer, Integer>, Vector<Tuple<Tuple<Integer, Integer>, Integer>>> successors = (t -> {
            Vector<Tuple<Tuple<Integer, Integer>, Integer>> vec = new Vector<>(4);
            vec.add(new Tuple<>(new Tuple<>(t.fst + 1, t.snd), 1));
            vec.add(new Tuple<>(new Tuple<>(t.fst - 1, t.snd), 1));
            vec.add(new Tuple<>(new Tuple<>(t.fst, t.snd + 1), 1));
            vec.add(new Tuple<>(new Tuple<>(t.fst, t.snd - 1), 1));
            return vec;
        });
        ToIntFunction<Tuple<Integer, Integer>> heuristic = (t -> Math.abs(t.fst - 5) + Math.abs(t.snd - 4));
        Predicate<Tuple<Integer, Integer>> goalTest = (t -> t.fst == 5 && t.snd == 4);
        AStar aStar = new AStar(initialState, successors, goalTest, heuristic);
        aStar.search();
        assertEquals(new Tuple<>(5,4), aStar.getFinalState());
        assertEquals(9, aStar.getOptimalCost());
    }
}