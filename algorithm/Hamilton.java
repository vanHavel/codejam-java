package algorithm;

// this class implements the dynamic programming algorithm to find shortest hamiltonian cycles
public class Hamilton {

    private int n;
    private int[][] weights;

    public Hamilton(int[][] weights) {
        this.weights = weights;
        this.n = weights.length;
    }

    // compute the length of the shortest hamiltonian cycle. n must be small (<30)
    public int shortestCycle() {
        int subsetN = 1 << (n-1);
        int[][] dp = new int[n][subsetN];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < subsetN; ++j) {
                dp[i][j] = -1;
            }
        }
        return compute(dp, 0, subsetN - 1);
    }

    // recurive solution with caching
    public int compute(int[][] dp, int start, int visits) {
        if (dp[start][visits] != -1) {
            return dp[start][visits];
        }
        else if (visits == 0) {
            return this.weights[start][0];
        }
        else {
            int val = Integer.MAX_VALUE;
            for (int i = 1; i < n; ++i) {
                if (bitIsSet(visits, i-1)) {
                    int rec = compute(dp, i, clearBit(visits, i-1));
                    val = Math.min(val, rec + this.weights[start][i]);
                }
            }
            dp[start][visits] = val;
            return val;
        }
    }

    // check whether i-th bit is set
    private boolean bitIsSet(int bin, int i) {
        return (bin & (1 << i)) != 0;
    }

    // clear the i-th bit
    private int clearBit(int bin, int i) {
        return (bin & ~(1 << i));
    }
}
