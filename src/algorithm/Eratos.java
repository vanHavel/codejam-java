package algorithm;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by lukashuwald on 08.02.18.
 */

// this class implements the prime number sieve of Eratosthenes
// use this for bounds n up to 10^8
public class Eratos {
    private Vector<Integer> primes;
    private int bound;

    // initialize eratos with upper bound
    public Eratos(int bound) {
        this.primes = new Vector<>(1000000);
        this.bound = bound;
    }

    // perform sieve to find all primes up to (and including) bound
    public void sieve() {
        if (this.bound < 2) {
            return;
        }
        int odds = (bound / 2) + (bound % 2);
        // isPrime stores primality for all odd numbers up to bound
        BitSet isPrime = new BitSet(odds);

        // inialize isPrime
        for (int i = 1; i < isPrime.size(); ++i) {
            isPrime.set(i);
        }
        this.primes.add(2);

        // only consider odd numbers > 2
        for (int i = 3; i <= bound; i += 2) {
            if (!isPrime.get(i/2)) {
                continue;
            }
            primes.add(i);
            for (int j = 3*i; j <= bound; j += 2*i) {
                isPrime.clear(j/2);
            }
        }
    }

    // returns vector of all primes sieved
    public Vector<Integer> getPrimes() {
        return this.primes;
    }

}
