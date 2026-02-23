package ru.courses.main;
import ru.courses.number.Fraction;

public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(3, 5);
        Number[] numList1 = {2, f1, 2.3f};

        Fraction f2 = new Fraction(49, 12);
        Fraction f3 = new Fraction(3, 2);
        Number[] numList2 = {3.6f, f2, 3, f3};

        Fraction f4 = new Fraction(1, 3);
        Number[] numList3 = {f4, 1};

        System.out.print("Sum numList1 = ");
        System.out.println(Fraction.sumAll(numList1));

        System.out.print("Sum numList2 = ");
        System.out.println(Fraction.sumAll(numList2));

        System.out.print("Sum numList3 = ");
        System.out.println(Fraction.sumAll(numList3));

    }
}
