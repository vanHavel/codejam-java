package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloydWarshallTest {

    @Test
    public void testFloydWarshall() {
        int[][] costs = new int[3][];
        costs[0] = new int[] {0, 5, 2};
        costs[1] = new int[] {-1, 0, -1};
        costs[2] = new int[] {3, 1, 0};

        int[][] dp = new FloydWarshall(costs).distances();

        assertEquals(3, dp[0][1]);
        assertEquals(-1, dp[1][0]);
        assertEquals(0, dp[1][1]);
        assertEquals(3, dp[2][0]);
    }
}