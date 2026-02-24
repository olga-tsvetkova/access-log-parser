import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.Duration.between;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> listUrl    = HashSet.newHashSet(10);;
    private HashSet<String> listBadUrl = HashSet.newHashSet(10);;
    private HashMap<String, Integer> osMap = new HashMap<>();
    private HashMap<String, Integer> brMap = new HashMap<>();;

    public Statistics() {
        clear();
    }

    public void clear() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.listUrl.clear(); //
        this.osMap.clear();
        this.brMap.clear();
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic = this.totalTraffic + entry.getDataSize();
                if (this.minTime == null || entry.getDateAndTime().isBefore(this.minTime)) {
            this.minTime = entry.getDateAndTime();
        }
        if (this.maxTime == null || entry.getDateAndTime().isAfter(this.maxTime)) {
            this.maxTime = entry.getDateAndTime();
        }

        if (entry.getStatusCode()==200) listUrl.add(entry.getPath());
        if (entry.getStatusCode()==404) listBadUrl.add(entry.getPath());
        String os= entry.getUaTag().getOsName();        // Получаем имя операционной системы из тега User-Agent
        String br = entry.getUaTag().getBrowserName();  // Получаем имя браузера из тега User-Agent
        if (osMap.containsKey(os)) osMap.put(os, osMap.getOrDefault(os, 0) + 1);
        else osMap.put(os, 1);
        if (brMap.containsKey(br)) brMap.put(br, brMap.getOrDefault(br, 0) + 1);
        else brMap.put(br, 1);
    }

    public List<String> getUrlList() {
        return new ArrayList<>(listUrl);
    }

    public List<String> getBadUrlList() {
        return new ArrayList<>(listBadUrl);
    }

    public HashMap<String, Double> getOsStatistics() {
        HashMap<String, Double> res = new  HashMap<>();
        AtomicInteger allCount = new AtomicInteger();
        osMap.forEach((s, cnt) -> allCount.addAndGet(cnt));
        osMap.forEach((s, cnt) -> res.put(s, (double) cnt / allCount.get() * 100));
        return res;
    }

    public HashMap<String, Double> getBrouserStatistics() {
        HashMap<String, Double> res = new  HashMap<>();
        AtomicInteger allCount = new AtomicInteger();
        brMap.forEach((s, cnt) -> allCount.addAndGet(cnt));
        brMap.forEach((s, cnt) -> res.put(s, (double) cnt / allCount.get() * 100));
        return res;
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