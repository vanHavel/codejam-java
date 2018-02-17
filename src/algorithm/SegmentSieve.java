package algorithm;

import java.util.BitSet;
import java.util.Vector;

// this class implements a segmented prime number sieve to sieve vast amounts of primes, and returns the number of primes
// use this for bounds n > 10^8
public class SegmentSieve {

    private long bound;

    public Vector<Integer> getSievingPrimes() {
        return this.sievingPrimes;
    }

    private Vector<Integer> sievingPrimes;

    // initialize the sieve with a bound
    public SegmentSieve(long bound) {
        this.bound = bound;
        this.sievingPrimes = new Vector<>();
    }

    public long sieve() {
        int sieveBound = ((int) Math.sqrt(this.bound)) + 1;
        //System.out.println(sieveBound);
        Eratos eratos = new Eratos(sieveBound);
        eratos.sieve();
        this.sievingPrimes = eratos.getPrimes();
        int sievingPrimeN = this.sievingPrimes.size();
        long primeN = sievingPrimeN;

        // number of odd numbers per segment
        int segmentSize = 1000000;
        // setup first segment
        long segmentStart = sieveBound + 1;
        if (segmentStart % 2 == 0) {
            segmentStart++;
        }
        long segmentEnd = segmentStart + 2 * segmentSize;
        Vector<Long> sieveMultiples = new Vector<>(sievingPrimeN);
        for (int i = 0; i < sievingPrimeN; ++i) {
            long candidate = segmentStart - (segmentStart % this.sievingPrimes.get(i));
            if (candidate < segmentStart) {
                candidate += this.sievingPrimes.get(i);
            }
            if (candidate % 2 == 0) {
                candidate += this.sievingPrimes.get(i);
            }
            sieveMultiples.add(i, candidate);
        }

        do {
            BitSet segment = new BitSet(segmentSize);
            segment.set(0, segmentSize);
            // sieve a single segment, do not sieve with 2
            for (int i = 1; i < sievingPrimeN; ++i) {
                while (sieveMultiples.get(i) < segmentEnd) {
                    long composite = sieveMultiples.get(i);
                    int clearIndex = ((int) (composite - segmentStart)) / 2;
                    segment.clear(clearIndex);
                    sieveMultiples.set(i, composite + 2 * sievingPrimes.get(i));
                }
            }
            // count new primes
            if (segmentEnd <= this.bound) {
                primeN += segment.cardinality();
            }
            else {
                // special case: last segment
                for (int i = 0; i < segmentSize; ++i) {
                    if (segmentStart + 2 * i > this.bound) {
                        break;
                    }
                    else if (segment.get(i)) {
                        primeN++;
                    }
                }
            }
            segmentStart += segmentSize * 2;
            segmentEnd += segmentSize * 2;
        } while (segmentStart < this.bound);
        return primeN;
    }

}
