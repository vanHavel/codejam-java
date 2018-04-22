package algorithm;

import data.Complex;

// this class implements the fast fourier transformation to transform coefficient-form polynomials
// into polynomials with valuations at roots of unity (and back)
public class FastFourierTransformer {

    // perform fft
    public static Complex[] fft(Complex[] coefficients) {
        // extend to power of 2
        int c = coefficients.length;
        int nearestPower = 1;
        while (c > nearestPower) {
            nearestPower *= 2;
        }
        if (c < nearestPower) {
            Complex[] newCoefficients = new Complex[nearestPower];
            for (int i = 0; i < c; ++i) {
                newCoefficients[i] = coefficients[i];
            }
            for (int i = c; i < nearestPower; ++i) {
                newCoefficients[i] = Complex.ZERO;
            }
            coefficients = newCoefficients;
        }

        // call recursive fft
        Complex omegaN = Complex.rootOfUnity(nearestPower);
        return FastFourierTransformer.fftDivideAndConquer(coefficients, omegaN);
    }

    // coefficients.length must be power of 2
    private static Complex[] fftDivideAndConquer(Complex[] coefficients, Complex omegaN) {
        int n = coefficients.length;
        if (n == 1) {
            return new Complex[] {coefficients[0]};
        }
        Complex omega = Complex.ONE;
        Complex[] evens = new Complex[n / 2];
        Complex[] odds = new Complex[n / 2];
        for (int i = 0; i < n / 2; ++i) {
            evens[i] = coefficients[2*i];
            odds[i] = coefficients[2*i+1];
        }
        Complex[] evenRec = FastFourierTransformer.fftDivideAndConquer(evens, omegaN.multiply(omegaN));
        Complex[] oddRec = FastFourierTransformer.fftDivideAndConquer(odds, omegaN.multiply(omegaN));
        Complex[] res = new Complex[n];
        for (int i = 0; i < n / 2; ++i) {
            res[i] = evenRec[i].add(omega.multiply(oddRec[i]));
            res[i + n / 2] = evenRec[i].subtract(omega.multiply(oddRec[i]));
            omega = omega.multiply(omegaN);
        }
        return res;
    }

    // valuations.length must be power of 2
    public static Complex[] inverseFft(Complex[] valuations) {
        int n = valuations.length;
        Complex omegaNInverse = Complex.ONE.divide(Complex.rootOfUnity(n));
        Complex[] ffted = FastFourierTransformer.fftDivideAndConquer(valuations, omegaNInverse);
        for (int i = 0; i < n; ++i) {
            ffted[i] = ffted[i].divide(Complex.realNumber(n));
        }
        return ffted;
    }

}
