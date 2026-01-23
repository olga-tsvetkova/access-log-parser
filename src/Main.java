import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Введите первое число:");
        int numberOne = new Scanner (System.in).nextInt();

        System.out.println("Введите второе число:");
        int numberTwo = new Scanner (System.in).nextInt();
        int resultInt;
        //сумма
        resultInt = numberOne + numberTwo;
        System.out.println("Сумма чисел : " + resultInt);
        //разность
        resultInt = numberOne - numberTwo;
        System.out.println("Разность чисел :" + resultInt);

        //умножение
        resultInt = numberOne * numberTwo;
        System.out.println("Произведение чисел :" + resultInt);

        //деление
        double resultDouble;
        resultDouble = (double) numberOne / numberTwo;
        System.out.println("Частное чисел :" + resultDouble);
    }
}
