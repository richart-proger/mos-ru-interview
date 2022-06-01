package ru.mos.interviewjava;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Основной класс с тестами.
 * Все входящие данные в тестах значащие: отдельная проверка на null и пустые строки не требуется.
 */
@SuppressWarnings({"SameReturnValue", "unused", "EmptyMethod"})
class MainTasks {

    /**
     * Хранилище счетов клиентов. Вспомогательное поле; тесты начинаются ниже.
     * <p/>
     * Структура:
     * <li>Integer - уникальный ID клиента</li>
     * <li>Integer - <u>имитатор</u> баланса клиента (в prod - BigDecimal)</li>
     */
    static final Map<Integer, Integer> accounts = new ConcurrentHashMap<>();

    /**
     * Экземпляр этого класса нельзя создавать
     */
    private MainTasks() {
    }

    /**
     * Тест "FizzBuzz".
     *
     * @param i число, которое нужно обработать
     * @return строка:
     * <li>если параметр делится нацело на 3 - строка "Fizz"
     * <li>если параметр делится нацело на 5 - строка "Buzz"
     * <li>если параметр делится нацело и на 3 и на 5 - строка "FizzBuzz"
     * <li>если параметр <b>не делится</b> нацело на 3 или на 5 - значение парамера в строковом виде
     */
    static String fizzBuzz(int i) {
        return "";
    }

    /**
     * Тест на работу со строками.
     *
     * @param cities список городов ({@link City}
     * @return строка со списком названий городов, разделёнными запятыми и пробелами, например - Москва, Сидней, Лондон.
     * Список должен заканчиваться точкой.
     */
    static String withComma(List<City> cities) {
        return "";
    }

    /**
     * Тест на работу с коллекциями.
     * <p>
     *     Требуется модифицировать список - удалить все города, начинающиеся на русскую букву "М"
     * </p>
     *
     * @param allCities список городов ({@link City}
     */
    static void notStartingWithMList(List<City> allCities) {
    }

    /**
     * Тест на работу с потоками (stream).
     * <p>
     *     Требуется посчитать суммарное население <u>крупных</u> городов
     *     (население каждого города должно быть больше указанного лимита)
     * </p>
     *
     * @param countryStream         - поток стран {@link Country}
     * @param populationThreshold - граничное условие для отбора городов. В выборку должны попасть города с населением
     *                              <b>больше</b> указанного лимита
     * @return long суммарное население для указанных городов
     */
    static long getSumPopulationFor(Stream<Country> countryStream, int populationThreshold) {
        return 0;
    }

    /**
     * Тест на работу с многопоточностью.
     * <p>
     *     Функция перевода денег.
     *     Требуется написать код, переводящий деньги со счёта на счёт в пределах банка.
     * <p/>
     * <b>Метод будет вызываться из нескольких потоков.</b>
     * Ниже представлены два вспомогательных метода.
     *
     * @param fromClient ключ клиента, со счёта которого нужно списать деньги
     * @param toClient   ключ клиента, на счёт которого нужно перевести деньги
     * @param amount     сумма перевода. Для учебных целей -  int
     */
    static void doTransfer(int fromClient, int toClient, int amount) {
    }

    /**
     * Вспомогательный метод для doTransfer().
     */
    static void setBalance(int client, int newBalance) {
        accounts.put(client, newBalance);
    }

    /**
     * Вспомогательный метод для doTransfer().
     */
    static int getBalance(int client) {
        return accounts.get(client);
    }

    /**
     * Тест на знание SQL.
     * <p>
     *     Напишите SQL-запрос, который возвращает названия отделов и суммы зарплат сотрудников в отделах,
     *     для тех отделов, у которых суммарный фонд оплаты труда меньше 250000.
     * <p>
     *
     * @return SQL-запрос:
     */
    static String getSelect() {
        /*
        Отделы
        department
        Id  Name
        ==================
        1   Коммерческий
        2   Юридический
        3   Разработки

        Сотрудники
        employee
        Id  Department Id   Name                    Salary
        ====================================================
        1   1               Коммерческий директор   250000
        2   2               Главный консультант     150000
        3   2               Консультант             80000
        4   3               Разработчик1            120000
        5   3               Разработчик2            120000
        6   3               Разработчик3            150000
        7   3               ТимЛид                  190000
        */
        return "";
    }
}
