package utility;

import java.util.Vector;

/**
 * Created by lukashuwald on 08.02.18.
 */
public class Eratos {

    private boolean[] isPrime;

    private Vector<Integer> primes;

    public Eratos(int bound) {
        this.primes = new Vector<>();
        this.isPrime = new boolean[bound+1];
        this.sieve();
    }

    private void sieve() {
        int bound = this.isPrime.length;
        if ((bound - 1) < 2) {
            return;
        }
        for (int i = 3; i < bound; ++i) {
            this.isPrime[i] = true;
        }
        this.primes.add(2);

        for (int i = 3; i < bound; i += 2) {
            if (!this.isPrime[i]) {
                continue;
            }
            primes.add(i);
            for (int j = 3*i; j < bound; j += 2*i) {
                isPrime[j] = false;
            }
        }
    }

    public boolean[] getIsPrime() {
        return this.isPrime;
    }

    public Vector<Integer> getPrimes() {
        return this.primes;
    }

}
