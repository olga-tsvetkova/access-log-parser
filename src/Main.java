import java.util.ArrayList;
import java.util.Arrays;

public class Main {
 public static void main(String[] args) throws IllegalAccessException {

     Cat cat = new Cat("Vasya", 10, new ArrayList<>(Arrays.asList("Anton", "Oleg", "Igor")));
     System.out.print("Cat: ");
     System.out.println(cat.toString());

     Nullifier.setAllReferenceFieldsToNull(cat);

     System.out.print("After: ");
     System.out.println(cat.toString());

 }
}
