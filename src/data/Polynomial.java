package data;

import algorithm.FastFourierTransformer;

// this class handles polynomials with complex coefficients
public class Polynomial {

    public final Complex[] coefficients;

    public Polynomial(Complex[] coefficients) {
        this.coefficients = coefficients;
    }

    // add two polynomials
    public Polynomial add(Polynomial rhs) {
        return Polynomial.add(this, rhs);
    }
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        int n = Math.max(p1.coefficients.length, p2.coefficients.length);
        Complex[] addedCoefficients = new Complex[n];
        for (int i = 0; i < n; ++i) {
            if (i < p1.coefficients.length) {
                addedCoefficients[i] = addedCoefficients[i].add(p1.coefficients[i]);
            }
            if (i < p2.coefficients.length) {
                addedCoefficients[i] = addedCoefficients[i].add(p2.coefficients[i]);
            }
        }
        return new Polynomial(addedCoefficients);
    }

    // multiply to polynomials using fft
    public Polynomial multiply(Polynomial rhs) {
        return Polynomial.multiply(this, rhs);
    }
    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        int productDegree = p1.coefficients.length + p2.coefficients.length;
        Complex[] coefficients1 = new Complex[productDegree];
        Complex[] coefficients2 = new Complex[productDegree];
        for (int i = 0; i < productDegree; ++i) {
            coefficients1[i] = i < p1.coefficients.length ? p1.coefficients[i] : Complex.ZERO;
            coefficients2[i] = i < p2.coefficients.length ? p2.coefficients[i] : Complex.ZERO;
        }
        Complex[] fft1 = FastFourierTransformer.fft(coefficients1);
        Complex[] fft2 = FastFourierTransformer.fft(coefficients2);
        int n = fft1.length;
        Complex[] multipliedValuations = new Complex[n];
        for (int i = 0; i < n; ++i) {
            multipliedValuations[i] =  fft1[i].multiply(fft2[i]);
        }
        Complex[] productCoefficients = FastFourierTransformer.inverseFft(multipliedValuations);
        return new Polynomial(productCoefficients);
    }

    // evaluate with horner schema
    public Complex evaluate(Complex x) {
        return Polynomial.evaluate(this, x);
    }
    public static Complex evaluate(Polynomial p, Complex x) {
        int n = p.coefficients.length;
        Complex res = p.coefficients[n-1];
        for (int i = n-2; n >= 0; --i) {
            res = res.multiply(x);
            res = res.add(p.coefficients[i]);
        }
        return res;
    }


}
