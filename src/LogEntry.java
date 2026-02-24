import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum HttpMethod {GET,POST,PUT,DELETE,HEAD,OPTIONS,TRACE,CONNECT,PATCH,NONE};
public class LogEntry {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss xx")
            .withLocale(java.util.Locale.ENGLISH);;

    private final String ipAddr;
    private final String dateAndTime ;
    private final HttpMethod method ;
    private final String path ;
    private final int statusCode ;
    private final long dataSize ;
    private final String referer ;
    private final String userAgent ;
    private final UserAgent uaTag;

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


    public LogEntry(String str) {
        // Разбор строки str и установка значений полей
            HttpMethod methodTmp = HttpMethod.NONE;
            String[] parts = parse(str);
            String   pathInfo = parts[5];
            String[] pathSplit;
            String   methodStr = null;
            String   pathStr   = null;
            if (pathInfo != null && pathInfo !="-" ) {
                pathSplit = pathInfo.split(" ");
                if (pathSplit.length>1) {
                    methodStr = pathSplit[0];
                    pathStr   = pathSplit[1];
                }
            }
            this.ipAddr = parts[0]+"."+parts[1]+"."+ parts[2]+"."+ parts[3];
            this.dateAndTime = parts[4];
            if (methodStr != null)
              try { methodTmp = HttpMethod.valueOf(methodStr); }
              catch (IllegalArgumentException e)  {methodTmp = HttpMethod.NONE;}
            this.method = methodTmp;
            this.path = pathStr;
            if (parts[6] != null && parts[6] !="-" )  this.statusCode =  Integer.parseInt(parts[6]);
            else this.statusCode = 0;
            if (parts[7] != null && parts[7] !="-" )  this.dataSize = Long.parseLong(parts[7]);
            else this.dataSize = 0;
            if (parts[8] != null && parts[8] !="-" ) this.referer = parts[8];
            else  this.referer = null;
            if (parts[9] != null && parts[8] !="-" )  this.userAgent = parts[9];
            else this.userAgent = "-";
            this.uaTag = new UserAgent(this.userAgent);
    }

    public UserAgent getUaTag() {
        return uaTag;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddr='" + ipAddr + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", method=" + method +
                ", path='" + path + '\'' +
                ", statusCode=" + statusCode +
                ", dataSize=" + dataSize +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", uaTag=" + uaTag +
                '}';
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getDateAndTime() {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(this.dateAndTime, formatter);
        return offsetDateTime.toLocalDateTime();
        //return LocalDateTime.now();
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getDataSize() {
        return dataSize;
    }

    public String getReferer() {
        return referer;
    }

    public String getUserAgent() {
        return userAgent;
    }
}

/*
Создайте класс LogEntry со свойствами (полями), соответствующими компонентам строк лог-файла:
   IP-адресу,дате и времени запроса, методу запроса, пути запроса, коду ответа, размеру отданных сервером данных, referer, а также User-Agent.
   Возможные методы HTTP-запросов положите в enum. Типы остальных полей определите самостоятельно.

● Для всех созданных в классе LogEntry свойств (полей) создайте геттеры, а сами свойства (поля) пометьте ключевым словом final.

● Создайте в классе LogEntry конструктор, который будет принимать в качестве единственного параметра строку,
  разбирать её на составляющие и устанавливать значения всех свойств (полей) класса.
*/