package algorithm;

public class FloydWarshall {

    private int[][] costs;

    // initialize with edge weights and source
    // use -1 for cost of non-edges
    public FloydWarshall (int[][] costs) {
        this.costs = costs;
    }

    // compute all shortest paths
    public int[][] distances() {
        int n = this.costs.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = costs[i][j];
            }
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    int val1 = dp[i][j];
                    int val2 = dp[i][k] + dp[k][j];
                    int res;
                    if (val1 == -1) {
                        res = val2;
                    }
                    else if (val2 == -1) {
                        res = val1;
                    }
                    else {
                        res = Math.min(val1, val2);
                    }
                    dp[i][j] = res;
                }
            }
        }

        return dp;
    }
}


