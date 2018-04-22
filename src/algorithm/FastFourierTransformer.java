package algorithm;

import data.Complex;

// this class implements the fast fourier transformation to transform coefficient-form polynomials
// into polynomials with valuations at roots of unity (and back)
public class FastFourierTransformer {

    public Complex[] fft(double[] coefficients) {
        // extend to power of 2
        int c = coefficients.length;
        int nearestPower = 1;
        while (c > nearestPower) {
            nearestPower *= 2;
        }
        if (c < nearestPower) {
            double[] newCoefficients = new double[nearestPower];
            for (int i = 0; i < c; ++i) {
                newCoefficients[i] = coefficients[i];
            }
            for (int i = c; i < nearestPower; ++i) {
                newCoefficients[i] = 0;
            }
            coefficients = newCoefficients;
        }

        // compute root of unity
        double omega = 1;
        // call recursive fft
        return fftDivideAndConquer(coefficients, omega);
    }

    private Complex[] fftDivideAndConquer(double[] coefficients, double omega) {
        return new Complex[0];
    }

    public double[] inverseFft(double[] valuations) {
        //TODO
        return new double[0];
    }
}
