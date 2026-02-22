package ru.courses.number;

public class Fraction_old {
    private final int numerator;
    private final int denominator;

    private static int findGCD(Fraction_old fraction) {
        int a = Math.abs(fraction.numerator);
        int b = fraction.denominator;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public Fraction_old(int numerator, int denominator) {
        if (denominator <= 0)
            throw new IllegalArgumentException("denominator is negative or zero");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction_old sum(Fraction_old other) {
        Fraction_old res = new Fraction_old(this.numerator * other.denominator + other.numerator * this.denominator,
                this.denominator * other.denominator);
        int gcd = findGCD(res);
        return new Fraction_old(res.numerator / gcd, res.denominator / gcd);
    }

    public Fraction_old minus(Fraction_old other) {
        Fraction_old res = new Fraction_old(this.numerator * other.denominator - other.numerator * this.denominator,
                this.denominator * other.denominator);
        int gcd = findGCD(res);
        return new Fraction_old(res.numerator / gcd, res.denominator / gcd);
    }

    public Fraction_old sum(int num) {
        Fraction_old other = new Fraction_old(num, 1);
        return this.sum(other);
    }

    public Fraction_old minus(int num) {
        Fraction_old other = new Fraction_old(num, 1);
        return this.minus(other);
    }

    @Override
    public String toString() {
        if (this.denominator == 1) return this.numerator + "";
        return this.numerator + "/" + this.denominator;
    }
}
