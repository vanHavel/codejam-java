package data;

import algorithm.FastFourierTransformer;

// this class handles polynomials with complex coefficients
public class Polynomial {

    // numbers with absolute value smaller then epsilon are treated like 0
    private static final double EPSILON = 1e-12;

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
            addedCoefficients[i] = Complex.ZERO;
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
        // fft
        Complex[] fft1 = FastFourierTransformer.fft(coefficients1);
        Complex[] fft2 = FastFourierTransformer.fft(coefficients2);
        // multiply
        int n = fft1.length;
        Complex[] multipliedValuations = new Complex[n];
        for (int i = 0; i < n; ++i) {
            multipliedValuations[i] =  fft1[i].multiply(fft2[i]);
        }
        // inverse fft
        Complex[] productCoefficients = FastFourierTransformer.inverseFft(multipliedValuations);
        // remove trailing zero coefficients
        int last = productCoefficients.length;
        while (last > 0 && productCoefficients[last-1].abs() < EPSILON) {
            last--;
        }
        if (last == productCoefficients.length) {
            return new Polynomial(productCoefficients);
        }
        Complex[] finalCoefficients = new Complex[last];
        for (int i = 0; i < last; ++i) {
            finalCoefficients[i] = productCoefficients[i];
        }
        return new Polynomial(finalCoefficients);
    }

    // evaluate with horner schema
    public Complex evaluate(Complex x) {
        return Polynomial.evaluate(this, x);
    }
    public static Complex evaluate(Polynomial p, Complex x) {
        int n = p.coefficients.length;
        Complex res = p.coefficients[n-1];
        for (int i = n-2; i >= 0; --i) {
            res = res.multiply(x);
            res = res.add(p.coefficients[i]);
        }
        return res;
    }


}
