package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    public void testMultiplication() {
        // 2 * x + 1
        Polynomial p1 = new Polynomial(new Complex[] {Complex.ONE, Complex.realNumber(2)});
        // x + 3
        Polynomial p2 = new Polynomial(new Complex[] {Complex.realNumber(3), Complex.ONE});

        Polynomial product = p1.multiply(p2);
        assertEquals(3, product.coefficients[0].real, 0.01);
        assertEquals(7, product.coefficients[1].real, 0.01);
        assertEquals(2, product.coefficients[2].real, 0.01);

        Polynomial biggerProduct = product.multiply(product);
        assertEquals(9, biggerProduct.coefficients[0].real, 0.01);
        assertEquals(42, biggerProduct.coefficients[1].real, 0.01);
        assertEquals(61, biggerProduct.coefficients[2].real, 0.01);
        assertEquals(28, biggerProduct.coefficients[3].real, 0.01);
        assertEquals(4, biggerProduct.coefficients[4].real, 0.01);
    }

}