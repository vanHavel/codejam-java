package algorithm;

import data.Pair;
import data.Tuple;

import java.util.List;

// this class implements the extended euclidean algorithm in integer rings,
// which allows to compute gcds and modular inverses
public class IntegerRings {

    // compute modular inverse, or return -1 if it does not exist
    public static long modularInverse(long i, long modulus) {
        Tuple<Long, Pair<Long>> eer = extendedEuclidean(i, modulus);
        if (eer.fst != 1) {
            return -1;
        }
        else {
            long candidate = eer.snd.fst;
            if (candidate < 0) {
                candidate *= -1;
                candidate = candidate % modulus;
                candidate = modulus - candidate;
            }
            else {
                candidate = candidate % modulus;
            }
            return candidate;
        }
    }

    // compute greatest common divisor
    public static long gcd(long i, long j) {
        return extendedEuclidean(i, j).fst;
    }

    // given two positive integers i, j:
    // compute gcd(i,j) and a,b such that a * j + b * i = gcd(i,j)
    // a and b are not unique and might be negative
    public static Tuple<Long, Pair<Long>> extendedEuclidean(long i, long j) {
        // if j is smaller swap the inputs
        if (j < i) {
            Tuple<Long, Pair<Long>> swapped = extendedEuclidean(j, i);
            return new Tuple<>(swapped.fst, new Pair<>(swapped.snd.snd, swapped.snd.fst));
        }
        // invariant: a * j + b * i = rest
        // initially: 1 * j + 0 * i = j and 0 * j + 1 * i = i
        long a = 0;
        long b = 1;
        long rest = i;
        long oldA = 1;
        long oldB = 0;
        long oldRest = j;
        // rest = oldRest % res, update a and b
        while (rest != 0) {
            long div = oldRest / rest;
            long tmp = rest;
            rest = oldRest - div * rest;
            oldRest = tmp;
            tmp = a;
            a = oldA - div * a;
            oldA = tmp;
            tmp = b;
            b = oldB - div * b;
            oldB = tmp;
        }
        return new Tuple<>(oldRest, new Pair<>(oldB, oldA));
    }

    // square and multiply with modulus
    public static long modularExponentiation(long base, long exponent, long modulus) {
        if (exponent == 0) {
            return 1;
        }
        else {
            long rec = modularExponentiation(base, exponent >> 1, modulus);
            long res = (rec * rec) % modulus;
            if (exponent % 2 == 1) {
                res = (res * base) % modulus;
            }
            return res;
        }
    }

    // identifies the unique integer x modulo the product of the constraint moduli satisfying the constraints
    // each constraint is a pair (a,b) meaning x = a % b
    // the values b must be pairwise relative prime
    public static Pair<Long> chineseRemainderTheorem(List<Pair<Long>> constraints) {
        int n = constraints.size();
        // handle trivial case
        if (n == 1) {
            return constraints.get(0);
        }
        // handle two equation case
        if (n == 2) {
            return crt(constraints.get(0), constraints.get(1));
        }
        // handle general case by recursion
        else {
            Pair<Long> fst = constraints.get(0);
            Pair<Long> snd = constraints.get(1);
            constraints = constraints.subList(2, constraints.size());
            constraints.add(0, crt(fst, snd));
            return chineseRemainderTheorem(constraints);
        }
    }

    // helper function for chinese remainder theorem that handles the two constraint case
    private static Pair<Long> crt(Pair<Long> constraint1, Pair<Long> constraint2) {
        long a1 = constraint1.fst;
        long a2 = constraint2.fst;
        long n1 = constraint1.snd;
        long n2 = constraint2.snd;

        // get bezout coefficients (gcd is 1 and can be ignored)
        Pair<Long> coeffs = extendedEuclidean(n1, n2).snd;
        long m1 = coeffs.fst;
        long m2 = coeffs.snd;

        // solution is a1m2n2 + a2m1n1
        long mod = n1 * n2;

        long res = a1 * m2 * n2 + a2 * m1 * n1;
        if (res < 0) {
            res = (res % mod) + mod;
        }
        res = res % mod;

        return new Pair<>(res, mod);
    }
}
