package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentSieveTest {

    @Test
    public void testSegmentSieve() {
        SegmentSieve segmentSieve = new SegmentSieve(13);
        long primes = segmentSieve.sieve();
        assertEquals(6, primes);
        // large test cases
        segmentSieve = new SegmentSieve(1000000);
        primes = segmentSieve.sieve();
        assertEquals(78498, primes);
        segmentSieve = new SegmentSieve(10000000);
        primes = segmentSieve.sieve();
        assertEquals(664579, primes);
    }

}