package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

    @Test
    public void testBasicOperations() {
        Complex c1 = new Complex(3, 4);
        Complex c2 = new Complex(2, 1);

        assertEquals(new Complex(5, 5), Complex.add(c1, c2));
        assertEquals(new Complex(5, 5), c1.add(c2));

        assertEquals(new Complex(1, 3), Complex.subtract(c1, c2));
        assertEquals(new Complex(1, 3), c1.subtract(c2));

        assertEquals(new Complex(2, 11), Complex.multiply(c1, c2));
        assertEquals(new Complex(2, 11), c1.multiply(c2));

        assertEquals(new Complex(2, 1), Complex.divide(c1, c2));
        assertEquals(new Complex(2, 1), c1.divide(c2));

        assertEquals(new Complex(4.5, 6), Complex.scale(c1, 1.5));
        assertEquals(new Complex(4.5, 6), c1.scale(1.5));
    }

    @Test
    public void testFunctions() {
        Complex c1 = new Complex(3,4);

        assertEquals(5, Complex.abs(c1));
        assertEquals(5, c1.abs());

        assertEquals(new Complex(3, -4), Complex.conjugate(c1));
        assertEquals(new Complex(3, -4), c1.conjugate());

        assertEquals(-13.128, Complex.exp(c1).real, 0.01);
        assertEquals(-15.2, Complex.exp(c1).imaginary, 0.01);
    }

}