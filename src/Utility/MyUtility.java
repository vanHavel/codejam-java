package Utility;

import java.util.AbstractList;
import java.util.List;

public class MyUtility {

    // turn string into list of characters
    public static List<Character> stringAsList(String s) {
        return new AbstractList<Character>() {
            @Override
            public Character get(int index) {
                return s.charAt(index);
            }

            @Override
            public int size() {
                return s.length();
            }
        };
    }

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
