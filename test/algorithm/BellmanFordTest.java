package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {

    @Test
    public void testWithoutNegativeCycles() {
        long[][] weights = new long[4][];
        weights[0] = new long[] {0,1,3,4};
        weights[1] = new long[] {2,0,1,6};
        weights[2] = new long[] {0,0,0,-1};
        weights[3] = new long[] {-1,0,2,0};

        BellmanFord bellmanFord = new BellmanFord(weights);
        bellmanFord.computeShortestPaths(0);
        long[] distances = bellmanFord.getDistances();
        int[] predecessors = bellmanFord.getPredecessors();
        assertArrayEquals(new long[] {0, 1, 2, 1}, distances);
        assertArrayEquals(new int[] {0, 0, 1, 2}, predecessors);
        assertFalse(bellmanFord.hasNegativeCycle());
    }

    @Test
    public void testWithNegativeCycle() {
        long[][] weights = new long[3][];
        weights[0] = new long[] {0,1,3};
        weights[1] = new long[] {2,0,-3};
        weights[2] = new long[] {1,2,0};

        BellmanFord bellmanFord = new BellmanFord(weights);
        bellmanFord.computeShortestPaths(0);
        assertTrue(bellmanFord.hasNegativeCycle());

    }

}