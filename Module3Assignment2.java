import java.math.BigInteger;

public class Rational extends Number implements Comparable<Rational> {
    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    public Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    public Rational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public Rational add(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator())
                .add(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    public Rational subtract(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator())
                .subtract(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    public Rational multiply(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getNumerator());
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    public Rational divide(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator());
        BigInteger d = denominator.multiply(secondRational.getNumerator());
        return new Rational(n, d);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator.toString();
        else
            return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Rational)) {
            return false;
        }
        Rational otherRational = (Rational) other;
        return numerator.equals(otherRational.numerator) &&
               denominator.equals(otherRational.denominator);
    }

    @Override
    public int intValue() {
        return numerator.intValue() / denominator.intValue();
    }

    @Override
    public float floatValue() {
        return numerator.floatValue() / denominator.floatValue();
    }

    @Override
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public long longValue() {
        return numerator.longValue() / denominator.longValue();
    }

    @Override
    public int compareTo(Rational other) {
        return numerator.multiply(other.denominator)
                .compareTo(denominator.multiply(other.numerator));
    }

    public static Rational parseRational(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Input should be in the format 'numerator denominator'");
        }
        BigInteger numerator = new BigInteger(parts[0]);
        BigInteger denominator = new BigInteger(parts[1]);
        return new Rational(numerator, denominator);
    }

    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);

        System.out.print("Enter the first rational number: ");
        Rational r1 = parseRational(input.nextLine());

        System.out.print("Enter the second rational number: ");
        Rational r2 = parseRational(input.nextLine());

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " is " + r2.doubleValue());
    }
}
