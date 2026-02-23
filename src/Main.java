import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
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
                System.out.println("Файл не существует: " + path);
                continue;
            } else {
                ++cnt;
                System.out.println("Путь указан верно: " + path);
                System.out.println("Это файл номер " + cnt);
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader = new BufferedReader(fileReader);
                    String line;
                    int count   = 0;
                    int maxSize = 0;
                    int minSize = Integer.MAX_VALUE - 1;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > 1024)
                            throw new IllegalArgumentException("Файл содержит строку более 1024 символов");
                        count += 1;
                        if (length > maxSize) { maxSize = length; }
                        if (length < minSize) { minSize = length; }
                    }
                    System.out.println("Всего строк в файле        = " + count);
                    System.out.println("Минимальный  размер строки = " + minSize);
                    System.out.println("Максимальный размер строки = " + maxSize);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
