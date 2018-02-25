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

    // calculate nChooseK in O(n) modulo a prime number
    public static long nChooseKModuloPrime(int n, int k, int modulus) {
            if (k > n / 2) {
                k = n - k;
            }
            if (k == 0) {
                return 1;
            }
            else if (n < k || n <= 0) {
                return 0;
            }
            else {
                int num = 1;
                int denom = 1;
                long numFactors = 0;
                long denomFactors = 0;
                for (int i = 0; i < k; ++i) {
                    int cur = n - i;
                    while (cur % modulus == 0) {
                        cur /= modulus;
                        numFactors++;
                    }
                    num = (num * cur) % modulus;
                    cur = i + 1;
                    while (cur % modulus == 0) {
                        cur /= modulus;
                        denomFactors++;
                    }
                    denom = (denom * cur) % modulus;
                }
                if (numFactors > denomFactors) {
                    return 0;
                }
                else {
                    int invDenom = (int) IntegerRings.modularInverse(denom, modulus);
                    return (num * invDenom) % modulus;
                }
            }
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
