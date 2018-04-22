package data;

import java.util.Objects;

// this class handles complex numbers and their operations
public class Complex {

    public final double real;
    public final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // add two numbers
    public static Complex add(Complex c1, Complex c2) {
        return new Complex(c1.real + c2.real, c1.imaginary + c2.imaginary);
    }
    public Complex add(Complex rhs) {
        return Complex.add(this, rhs);
    }

    // subtract two numbers
    public static Complex subtract(Complex c1, Complex c2) {
        return new Complex(c1.real - c2.real, c1.imaginary - c2.imaginary);
    }
    public Complex subtract(Complex rhs) {
        return Complex.subtract(this, rhs);
    }

    // multiply two numbers
    public static Complex multiply(Complex c1, Complex c2) {
        return new Complex(c1.real * c2.real - c1.imaginary * c2.imaginary,
                c1.real * c2.imaginary + c2.real * c1.imaginary);
    }
    public Complex multiply(Complex rhs) {
        return Complex.multiply(this, rhs);
    }

    // divide two numbers
    public static Complex divide(Complex c1, Complex c2) {
        double denom = c2.real * c2.real + c2.imaginary * c2.imaginary;
        double realNum = c1.real * c2.real + c1.imaginary * c2.imaginary;
        double imagNum = c2.real * c1.imaginary - c1.real * c2.imaginary;
        return new Complex(realNum / denom, imagNum / denom);
    }
    public Complex divide(Complex rhs) {
        return Complex.divide(this, rhs);
    }

    // compute absolute value
    public static double abs(Complex c) {
        return Math.sqrt(c.real * c.real + c.imaginary * c.imaginary);
    }
    public double abs() {
        return Complex.abs(this);
    }

    // compute conjugate
    public static Complex conjugate(Complex c) {
        return new Complex(c.real, -c.imaginary);
    }
    public Complex conjugate() {
        return Complex.conjugate(this);
    }

    // scale by real number
    public static Complex scale(Complex c, double d) {
        return new Complex(c.real * d, c.imaginary * d);
    }
    public Complex scale(double d) {
        return Complex.scale(this, d);
    }

    // complex exp
    public static Complex exp(Complex exponent) {
        Complex iPower = new Complex(Math.cos(exponent.imaginary), Math.sin(exponent.imaginary));
        return Complex.scale(iPower, Math.exp(exponent.real));
    }

    // return n-th primitive root of unity
    public static Complex rootOfUnity(int n) {
        return Complex.exp(
                new Complex(0, 2 * Math.PI / n)
        );
    }

    // some constants
    public static final Complex ONE = new Complex(1,0);
    public static final Complex ZERO = new Complex(0,0);
    public static final Complex I = new Complex(0,1);
    public static final Complex E = new Complex(Math.E, 0);

    // real numbers
    public static Complex realNumber(double d) {
        return new Complex(d, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.real, real) == 0 &&
                Double.compare(complex.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public String toString() {
        return "Complex{" +
                "real=" + real +
                ", imaginary=" + imaginary +
                '}';
    }
}
