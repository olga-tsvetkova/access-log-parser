public class UserAgent {
    private final String osName;
    private final String browserName;

    public UserAgent(String userAgent) {
        if (userAgent.contains("Windows")) {
            this.osName = "Windows";
        } else if (userAgent.contains("Macintosh")) {
            this.osName = "macOS";
        } else if (userAgent.contains("Linux")) {
            this.osName = "Linux";
        } else {
            this.osName = "Unknown OS";
        }

        if (userAgent.contains("Edge")) {
            this.browserName = "Edge";
        } else if (userAgent.contains("Firefox")) {
            this.browserName = "Firefox";
        } else if (userAgent.contains("Chrome")) {
            this.browserName = "Chrome";
        } else if (userAgent.contains("Opera")) {
            this.browserName = "Opera";
        } else {
            this.browserName = "Unknown browser";
        }
    }

    public String getOsName() {
        return osName;
    }

    public String getBrowserName() {
        return browserName;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "osName='" + osName + '\'' +
                ", browserName='" + browserName + '\'' +
                '}';
    }
}

/*
* Создайте класс UserAgent по тому же принципу: с final-свойствами (полями), соответствующими свойствам, заданным в строке User-Agent (см. ниже),
     и геттерами для этих свойств.

● Создайте также в классе UserAgent конструктор, который будет принимать в качестве параметра строку User-Agent и извлекать из неё свойства.
 Для этого воспользуйтесь методом String.contains().Из строки User-Agent необходимо извлекать два свойства:
 тип операционной системы (Windows, macOS или Linux)
 тип браузера (Edge, Firefox, Chrome, Opera или другой).
 Для определения типа операционной системы и браузера воспользуйтесь инструкцией.
*/