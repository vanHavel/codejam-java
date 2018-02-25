package algorithm;

import data.Tuple;

import java.util.LinkedList;
import java.util.List;

// this class implements combinatorical algorithms, such as computing binomial coefficients
public class Combinatorics {

    // calculate binomial coefficient (n choose k) modulo modulus
    // implemented separately from combinations because we can do this in O(k) space
    public static long nChooseK(int n, int k, long modulus) {
        long[] dp = new long[k+1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j >= 1; --j) {
                dp[j] = (dp[j] + dp[j-1]) % modulus;
            }
        }
        return dp[k];
    }

    // calculate nChooseK in O(n) for small modulus, for a list of inputs (n,k)
    public static List<Integer> smallModulusNChooseK(List<Tuple<Integer, Integer>> nks, int modulus) {
        // precompute inverses modulo modulus
        int[] inverses = new int[modulus];
        for (int i = 1; i < modulus; ++i) {
            inverses[i] = (int) IntegerRings.modularInverse(i, modulus);
        }
        // evaluate factorials
        List<Integer> coeffs = new LinkedList<>();
        for (Tuple<Integer, Integer> nk : nks) {
            int n = nk.fst;
            int k = nk.snd;
            int res = 1;
            for (int i = 0; i < k; ++i) {
                res = (res * (n - i)) % modulus;
            }
            for (int i = 0; i < (n - k); ++i) {
                res = (res * inverses[i]) % modulus;
            }
            coeffs.add(res);
        }
        return coeffs;
    }


    // calculate all binomial coefficients until n, k modulo modulus
    public static long[][] combinations(int n, int k, long modulus) {
        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j >= 1; --j) {
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % modulus;
            }
            dp[i][0] = 1;
        }
        return dp;
    }


}
