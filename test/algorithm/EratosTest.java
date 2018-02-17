package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EratosTest {

    @Test
    public void testEratos() {
        // small test case
        Eratos eratos = new Eratos(13);
        eratos.sieve();
        assertEquals(6, eratos.getPrimes().size());
        // large test cases
        eratos = new Eratos(1000);
        eratos.sieve();
        assertEquals(168, eratos.getPrimes().size());
        eratos = new Eratos(1000000);
        eratos.sieve();
        assertEquals(78498, eratos.getPrimes().size());
        eratos = new Eratos(10000000);
        eratos.sieve();
        assertEquals(664579, eratos.getPrimes().size());
    }
}