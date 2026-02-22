
public class Fraction extends Number {
    private final int numerator;
    private final int denominator;

    private static int findGCD(Fraction  fraction) {
        int a = Math.abs (fraction.numerator);
        int b = fraction.denominator;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public Fraction(int numerator, int denominator) {
        if (denominator <= 0)
            throw new IllegalArgumentException("denominator is negative or zero");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        if (this.denominator == 1) return this.numerator + "";
        return this.numerator + "/" + this.denominator;
    }

    @Override
    public int intValue() {
        return numerator / denominator;
    }

    @Override
    public long longValue() {
        return numerator / denominator;
    }

    @Override
    public float floatValue() {
        return ((float)numerator)/denominator;
    }

    @Override
    public double doubleValue() {
        return ((double)numerator)/denominator;
    }

    public Fraction  sum(Fraction  other) {
        Fraction  res = new Fraction (this.numerator * other.denominator + other.numerator * this.denominator,
                this.denominator * other.denominator);
        int gcd = findGCD(res);
        return new Fraction (res.numerator / gcd, res.denominator / gcd);
    }

    public Fraction  minus(Fraction  other) {
        Fraction  res = new Fraction (this.numerator * other.denominator - other.numerator * this.denominator,
                this.denominator * other.denominator);
        int gcd = findGCD(res);
        return new Fraction (res.numerator / gcd, res.denominator / gcd);
    }

    public Fraction  sum(int num) {
        Fraction other = new Fraction (num, 1);
        return this.sum(other);
    }

    public Fraction  minus(int num) {
        Fraction  other = new Fraction (num, 1);
        return this.minus(other);
    }


    public static float  sumAll(Number[] numList) {
        float res= 0.0f;
        for (Number num : numList) res += num.floatValue();
        return res;
    }

}
