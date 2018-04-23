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
        assertEquals(3, product.coefficients.length);

        Polynomial biggerProduct = product.multiply(product);
        assertEquals(9, biggerProduct.coefficients[0].real, 0.01);
        assertEquals(42, biggerProduct.coefficients[1].real, 0.01);
        assertEquals(61, biggerProduct.coefficients[2].real, 0.01);
        assertEquals(28, biggerProduct.coefficients[3].real, 0.01);
        assertEquals(4, biggerProduct.coefficients[4].real, 0.01);
        assertEquals(5, biggerProduct.coefficients.length);
    }

    @Test
    public void testAddition() {
        // x^2 + 2x + 3
        Polynomial p1 = new Polynomial(new Complex[]{
                Complex.realNumber(3), Complex.realNumber(2), Complex.realNumber(1)
        });
        // x + i
        Polynomial p2 = new Polynomial(new Complex[]{Complex.I, Complex.ONE});

        Polynomial sum = p1.add(p2);
        assertEquals(new Complex(3, 1), sum.coefficients[0]);
        assertEquals(new Complex(3, 0), sum.coefficients[1]);
        assertEquals(new Complex(1, 0), sum.coefficients[2]);
        assertEquals(3, sum.coefficients.length);
    }

    @Test
    public void testEvaluation() {
        // x^2 + 2x + 3
        Polynomial p1 = new Polynomial(new Complex[] {
                Complex.realNumber(3), Complex.realNumber(2), Complex.realNumber(1)
        });
        assertEquals(Complex.realNumber(3), p1.evaluate(Complex.realNumber(0)));
        assertEquals(Complex.realNumber(38), p1.evaluate(Complex.realNumber(5)));
        assertEquals(new Complex(2, 2), p1.evaluate(Complex.I));
    }

}