package algorithm;

import java.util.Vector;

/**
 * Created by lukashuwald on 08.02.18.
 */

// this class implements the prime number sieve of Eratosthenes
// use this for bounds n up to 10^8
public class Eratos {

    private boolean[] isPrime;
    private Vector<Integer> primes;
    private int bound;

    // initialize eratos with upper bound
    public Eratos(int bound) {
        this.primes = new Vector<>(1000000);
        this.bound = bound;
        int odds = (bound / 2) + (bound % 2);
        // is Prime stores primality for all odd numbers up to bound
        this.isPrime = new boolean[odds];
    }

    // perform sieve to find all primes up to (and including) bound
    public void sieve() {
        if ((this.bound - 1) < 2) {
            return;
        }
        for (int i = 1; i < this.isPrime.length; ++i) {
            this.isPrime[i] = true;
        }
        this.primes.add(2);

        // only consider odd numbers > 2
        for (int i = 3; i <= bound; i += 2) {
            if (!this.isPrime[i / 2]) {
                continue;
            }
            primes.add(i);
            for (int j = 3*i; j <= bound; j += 2*i) {
                isPrime[j / 2] = false;
            }
        }
    }

    // returns vector of all primes sieved
    public Vector<Integer> getPrimes() {
        return this.primes;
    }

}
