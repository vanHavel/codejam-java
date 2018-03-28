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
    public static int nChooseKModuloPrime(int n, int k, int modulus) {
        if (k > n) {
            return 0;
        }
        else if (n < modulus) {
            int num = 1;
            int denom = 1;
            for (int i = 0; i < k; ++i) {
                num = (num * (n-i)) % modulus;
                denom = (denom * (i+1)) % modulus;
            }
            return (num * (int) IntegerRings.modularInverse(denom, modulus)) % modulus;
        }
        else {
            int nDiv = n / modulus;
            int nMod = n % modulus;
            int kDiv = k / modulus;
            int kMod = k % modulus;
            return (nChooseKModuloPrime(nDiv, kDiv, modulus) *
                    nChooseKModuloPrime(nMod, kMod, modulus)) % modulus;
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