package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BipartiteMatchingTest {

    @Test
    public void testBipartiteMatching() {
        boolean[][] graph = new boolean[3][4];
        graph[0] = new boolean[] {true, false, false, false};
        graph[1] = new boolean[] {true, false, true, false};
        graph[2] = new boolean[] {false, false, true, false};

        BipartiteMatching bm = new BipartiteMatching(graph);
        bm.computeMaximumMatching();
        assertEquals(bm.getMatchingSize(), 2);
    }

}