package algorithm;

import data.Tuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FordFulkersonTest {

    @Test
    public void testFordFulkerson() {
        int[][] capacities = new int[5][5];
        capacities[0] = new int[] {0, 1, 2, 2, 0};
        capacities[1] = new int[] {0, 0, 0, 0, 2};
        capacities[2] = new int[] {0, 1, 0, 1, 1};
        capacities[3] = new int[] {0, 0, 1, 0, 1};
        capacities[4] = new int[] {0, 0, 0, 0, 0};

        FordFulkerson fordFulkerson = new FordFulkerson(capacities);
        Tuple<Integer, int[][]> maxFlow = fordFulkerson.maxFlow();
        assertEquals(Integer.valueOf(4), maxFlow.fst);
        assertEquals(1, maxFlow.snd[0][1]);
    }
}