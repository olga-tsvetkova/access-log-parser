import java.io.*;
import java.util.Scanner;


import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
                Statistics statistics = new Statistics();
                System.out.println("Путь указан верно: " + path);
                System.out.println("Это файл номер " + cnt);
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader = new BufferedReader(fileReader);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > 1024)
                            throw new IllegalArgumentException("Файл содержит строку более 1024 символов");
                        LogEntry le = new LogEntry(line);
                        statistics.addEntry(le);
                        }
                    } catch (IOException e)  {
                                                System.out.println("Ошибка чтения файла: " + path);
                                                throw new RuntimeException(e);
                                             }
                System.out.println("Средний трафик за час: " + statistics.getTrafficRate());
            }
        }
    }
}
