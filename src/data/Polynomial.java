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
        Complex[] fft1 = FastFourierTransformer.fft(p1.coefficients);
        Complex[] fft2 = FastFourierTransformer.fft(p2.coefficients);
        int n = Math.max(fft1.length, fft1.length);
        Complex[] multipliedValuations = new Complex[n];
        for (int i = 0; i < n; ++i) {
            multipliedValuations[i] = Complex.ONE;
            if (i < fft1.length) {
                multipliedValuations[i] =  multipliedValuations[i].multiply(fft1[i]);
            }
            if (i < fft2.length) {
                multipliedValuations[i] =  multipliedValuations[i].multiply(fft2[i]);
            }
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
