import java.time.LocalDateTime;
import static java.time.Duration.between;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        clear();
    }

    public void clear() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic = this.totalTraffic + entry.getDataSize();
                if (this.minTime == null || entry.getDateAndTime().isBefore(this.minTime)) {
            this.minTime = entry.getDateAndTime();
        }
        if (this.maxTime == null || entry.getDateAndTime().isAfter(this.maxTime)) {
            this.maxTime = entry.getDateAndTime();
        }
    }

    public long getTrafficRate()  {
        if (this.minTime != null && this.maxTime != null) {
            long durationInHours = between(minTime, maxTime).toHours();
            if (durationInHours > 0) {
                return (long) (totalTraffic / durationInHours);
            }
        }
        return 0;
    }
}

/*
 Создайте класс для расчётов статистики — Statistics. У этого класса должен быть конструктор без параметров, в котором должны инициализироваться переменные класса.

● Добавьте в класс Statistics метод addEntry, принимающий в качестве параметра объект класса LogEntry.

● Реализуйте в классе Statistics подсчёт среднего объёма трафика сайта за час. Для этого:

  создайте у класса свойство (поле) int totalTraffic, в которое в методе addEntry добавляйте объём данных, отданных сервером;
  создайте свойства (поля) minTime и maxTime класса LocalDateTime и заполняйте их в методе addEntry,
  если время в добавляемой записи из лога меньше minTime или больше maxTime соответственно;
  реализуйте в классе метод getTrafficRate, в котором вычисляйте разницу между maxTime и minTime в часах и делите общий объём трафика на эту разницу.
*/