import java.util.ArrayList;
import java.util.List;

public class Cat {
    private static String breed = "Persian"; // порода
    public String name;
    private int age;
    private List<String> friendsName = new ArrayList<>();

    public Cat(String name, int age, List<String> friendsName) {
        this.name = name;
        this.age = age;
        this.friendsName = friendsName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendsName=" + friendsName +
                '}';
    }
}