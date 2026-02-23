import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;


import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    public static String[] parse(String str ) {
        String[] res = new  String[10];
        int cnt = 0;
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|\\[(.*?)\\]|(\\d+)");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find() && (cnt <10 )) {
            if (matcher.group(1) != null) {
                res[cnt] = matcher.group(1);
            } else if (matcher.group(3) != null) {
                res[cnt] = matcher.group(3);
            } else {
                res[cnt] = matcher.group(2);
            }
            cnt++;
        }
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
       Scanner in_scan = new Scanner(System.in);
        String path;
        File file;
        boolean fileExists;
        boolean directoryExists;
        int cnt = 0;
        String[] tagLog;

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
                    int countG   = 0;
                    int countY   = 0;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > 1024)
                            throw new IllegalArgumentException("Файл содержит строку более 1024 символов");

                        tagLog = parse(line);
                        if (tagLog.length > 9 && tagLog[9] != null) {
                            if (tagLog[9].contains("Googlebot")) countG++;
                            if (tagLog[9].contains("YandexBot")) countY++;
                        }
                    }
                    System.out.println("Запросов от Googlebot: " + countG);
                    System.out.println("Запросов от YandexBot: " + countY);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
