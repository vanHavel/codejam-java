package algorithm;

import data.Edge;
import data.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BipartiteMatchingTest {

    @Test
    public void testBipartiteMatching() {
        boolean[][] graph = new boolean[3][4];
        graph[0] = new boolean[] {true, false, false, false};
        graph[1] = new boolean[] {true, false, true, false};
        graph[2] = new boolean[] {false, false, true, false};

        BipartiteMatching bm = new BipartiteMatching(graph);
        Tuple<Integer, Set<Edge>> res = bm.maximumBipartiteMatching();
        assertEquals(res.fst, Integer.valueOf(2));
    }

}