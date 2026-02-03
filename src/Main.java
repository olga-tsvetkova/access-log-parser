import java.io.File;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
     Scanner in_scan = new Scanner(System.in);
     String path;
     File file;
     boolean fileExists;
     boolean directoryExists;
     int cnt = 0;

     while (true) {
         // получить имя файла
         System.out.println("------------------------------------------");
         System.out.println("Введите путь к файлу: ");
         path = in_scan.nextLine();
         // создать объект File
         file = new File(path);
         fileExists = file.exists();
         directoryExists = file.isDirectory();
         if (directoryExists) {
             System.out.println("Это директория: " + path);
             continue;
         } else if (!fileExists) {
             System.out.println("Файл не существует: "+path);
             continue;
         } else {
             ++cnt;
             System.out.println("Путь указан верно: "+path);
             System.out.println("Это файл номер "+cnt);
         }
     }
    }
}
