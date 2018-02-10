package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HamiltonTest {

    @Test
    public void testHamilton() {

        // zero costs
        final int size = 5;
        int[][] zeros = new int[size][size];
        Hamilton hamilton = new Hamilton(zeros);
        int res = hamilton.shortestCycle();
        assertEquals(res, 0);

        // uniform costs
        int[][] uniform = new int[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (j == i) {
                    continue;
                }
                uniform[i][j] = 1;
            }
        }

        hamilton = new Hamilton(uniform);
        res = hamilton.shortestCycle();
        assertEquals(res, size);

        // mixed costs
        final int smallSize = 3;
        int[][] costs = new int[smallSize][smallSize];
        costs[0] = new int[] {0,1,4};
        costs[1] = new int[] {1,0,10};
        costs[2] = new int[] {3,3,0};
        hamilton = new Hamilton(costs);
        res = hamilton.shortestCycle();
        assertEquals(res, 8);
    }
}