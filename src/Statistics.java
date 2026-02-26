import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.Duration.between;

public class Statistics {
    private long totalTraffic;
    private long totalCall;
    private long totalBadCall;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> listUrl    = HashSet.newHashSet(10);
    private HashSet<String> listOutUrl    = HashSet.newHashSet(10);
    private HashSet<String> listUrlUser= HashSet.newHashSet(10);
    private HashSet<String> listBadUrl = HashSet.newHashSet(10);
    private HashMap<String, Integer> osMap = new HashMap<>();
    private HashMap<String, Integer> brMap = new HashMap<>();
    private HashMap<Integer, Integer> secMap= new HashMap<>();
    private HashMap<String, Integer>  usrMap= new HashMap<>();

    public static Integer getSec(LocalDateTime dt) {// получить секунды из локального времени (в рамках одного дня)
        return dt.getSecond() + 60 * dt.getMinute() + 3600 * dt.getHour();
    }

    public Statistics() {
        clear();
    }

    public void clear() {
        this.totalTraffic = 0;
        this.totalCall= 0;
        this.totalBadCall = 0;
        this.minTime = null;
        this.maxTime = null;
        this.listUrl.clear(); //
        this.listUrlUser.clear();
        this.osMap.clear();
        this.brMap.clear();
        this.secMap.clear();
        this.listOutUrl.clear();
        this.usrMap.clear();
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic = this.totalTraffic + entry.getDataSize();
                if (this.minTime == null || entry.getDateAndTime().isBefore(this.minTime)) {
            this.minTime = entry.getDateAndTime();
        }
        if (this.maxTime == null || entry.getDateAndTime().isAfter(this.maxTime)) {
            this.maxTime = entry.getDateAndTime();
        }
        // статистика посещений пользователями
        if (!entry.getUaTag().getBot()) {
            this.totalCall++;
            if (!entry.getReferer().equals("-"))  // если есть реферер - добавляем его хост в список внешних ссылок
               try {
                    URL url = new URL (entry.getReferer());
                    listOutUrl.add(url.getHost());
                   }
                catch (Exception e ) {
                    //  пропускаем ошибку
                    //  System.out.println("ошибка при обработке строки " + entry.getReferer() + " " + e.getMessage());
               }
            String addr = entry.getIpAddr();
            if (!listUrlUser.contains(addr)) listUrlUser.add(addr);

            if (usrMap.containsKey(addr))
                usrMap.put(addr, usrMap.getOrDefault(addr, 0) + 1);
            else usrMap.put(addr, 1);

            // статистика обращений по секундам
            // считаем, что время добавления строк в журнал НЕ уменьшается
            Integer sec = getSec(entry.getDateAndTime());
            if (secMap.containsKey(sec))
                secMap.put(sec, secMap.getOrDefault(sec, 0) + 1);
            else secMap.put(sec, 1);
        }


        // статистика ошибочных обращений по коду ответа  4хх и 5хх
        if (entry.getStatusCode()/100 ==4 || entry.getStatusCode()/100 ==5) this.totalBadCall ++;
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

    // Метод, возвращающий список сайтов, со страниц которых есть ссылки на текущий сайт.
    public void getOutUrlList() {
        listOutUrl.forEach(x->System.out.println(x));
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

    public long getUserRate()  {
        if (this.minTime != null && this.maxTime != null) {
            long durationInHours = between(minTime, maxTime).toHours();
            if (durationInHours > 0) {
                return (long) (totalCall / durationInHours);
            }
        }
        return 0;
    }

    public long getRatePerUser()  {
        AtomicInteger allCount = new AtomicInteger();
        listUrlUser.forEach((s) -> allCount.addAndGet(1));
        return (long) (totalCall / allCount.get());
    }

    public long getBadRequestRate()  {
        if (this.minTime != null && this.maxTime != null) {
            long durationInHours = between(minTime, maxTime).toHours();
            if (durationInHours > 0) {
                return (long) (totalBadCall / durationInHours);
            }
        }
        return 0;
    }

    // Метод расчёта пиковой нагрузки на сайт в секунду
    public Integer getPeakCall() {
        if (secMap.isEmpty())  return 0;

        Optional<Map.Entry<Integer, Integer>> maxEntry = secMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        return maxEntry.get().getValue();
    }

    // Метод расчёта максимальной посещаемости одним пользователем.
    public Integer getMaxUserCall() {
        // Если нет записей в usrMap, то возвращаем 0. Это предотвращает ошибку при вызове maxEntry.get().getValue(); когда usrMap пустой.
        if (usrMap.isEmpty())  return 0;

        Optional<Map.Entry<String, Integer>> maxEntry = usrMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        return maxEntry.get().getValue();
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