package algorithm;

import data.Tuple;

// this class implements the extended euclidean algorithm in integer rings,
// which allows to compute gcds and modular inverses
public class IntegerRings {

    // compute modular inverse, or return -1 if it does not exist
    public static long modularInverse(long i, long modulus) {
        Tuple<Long, Tuple<Long, Long>> eer = extendedEuclidean(i, modulus);
        if (eer.fst != 1) {
            return -1;
        }
        else {
            return eer.snd.fst;
        }
    }

    // compute greatest common divisor
    public static long gcd(long i, long j) {
        return extendedEuclidean(i, j).fst;
    }

    // given two positive integers i, j:
    // compute gcd(i,j) and a,b such that a * j + b * i = gcd(i,j)
    public static Tuple<Long, Tuple<Long, Long>> extendedEuclidean(long i, long j) {
        // if j is smaller swap the inputs
        if (j < i) {
            Tuple<Long, Tuple<Long, Long>> swapped = extendedEuclidean(j, i);
            return new Tuple<>(swapped.fst, new Tuple<>(swapped.snd.snd, swapped.snd.fst));
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
        return new Tuple<>(oldRest, new Tuple<>(oldB, oldA));
    }
}
