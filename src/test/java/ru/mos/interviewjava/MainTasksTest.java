package ru.mos.interviewjava;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Stream;

public class MainTasksTest {

    final static int CLIENTS = 5;

    private static final City MOW = new City("Москва", 15_000_000);
    private static final City LEN = new City("Санкт-Петербург", 12_000_000);
    private static final City TOK = new City("Токио", 23_000_000);
    private static final City OSA = new City("Осака", 4_200_000);
    private static final City SYD = new City("Сидней", 7_000_000);
    private static final City MEL = new City("Мельбурн", 5_160_000);
    private static final City SIN = new City("Сингапур", 14_000_000);

    @Test
    public void testFizzBuzz() {

        String[] answers = {
                "1",
                "2",
                //region ... прочие значения
                "Fizz",
                "4",
                "Buzz",
                "Fizz",
                "7",
                "8",
                "Fizz",
                "Buzz",
                "11",
                "Fizz",
                "13",
                "14",
                "FizzBuzz",
                "16",
                "17",
                "Fizz",
                "19",
                "Buzz",
                "Fizz",
                "22",
                "23",
                "Fizz",
                "Buzz",
                "26",
                "Fizz",
                "28",
                "29",
                "FizzBuzz",
                "31",
                "32",
                "Fizz",
                "34",
                "Buzz",
                "Fizz",
                "37",
                "38",
                "Fizz",
                "Buzz",
                "41",
                "Fizz",
                "43",
                "44",
                "FizzBuzz",
                "46",
                "47",
                "Fizz",
                "49",
                "Buzz",
                "Fizz",
                "52",
                "53",
                "Fizz",
                "Buzz",
                "56",
                "Fizz",
                "58",
                "59",
                "FizzBuzz",
                "61",
                "62",
                "Fizz",
                "64",
                "Buzz",
                "Fizz",
                "67",
                "68",
                "Fizz",
                "Buzz",
                "71",
                "Fizz",
                "73",
                "74",
                "FizzBuzz",
                "76",
                "77",
                "Fizz",
                "79",
                "Buzz",
                "Fizz",
                "82",
                "83",
                "Fizz",
                "Buzz",
                "86",
                "Fizz",
                "88",
                "89",
                "FizzBuzz",
                "91",
                "92",
                "Fizz",
                "94",
                "Buzz",
                "Fizz",
                "97",
                //endregion
                "98",
                "Fizz",
                "Buzz"
        };

        for (int i = 1; i <= 100; i++) {
            assertEquals(MainTasks.fizzBuzz(i), answers[i - 1], "Ошибка для входного значения " + i);
        }
    }

    @Test
    public void testWithComma() {
        assertEquals(MainTasks.withComma(Collections.emptyList()), "");
        assertEquals(MainTasks.withComma(getCityListForString()), "Москва, Токио, Сидней, Сингапур.");
    }

    @Test
    public void testNotStartingWithMList() {
        List<City> cities = getCityListForDeletion();
        MainTasks.notStartingWithMList(cities);
        assertEquals(cities.size(), 5);
        assertTrue(cities.contains(LEN));
        assertTrue(cities.contains(TOK));
        assertTrue(cities.contains(OSA));
        assertTrue(cities.contains(SYD));
        assertTrue(cities.contains(SIN));
    }

    @Test
    public void testGetSumPopulationFor() {
        assertEquals(MainTasks.getSumPopulationFor(getCountryStream(), 10_000_000), 64_000_000);
    }

    @Test
    public void testMultiThreading() throws InterruptedException {

        assertTrue(MainTasks.accounts.isEmpty(), "Хранилище не пустое");


        MainTasks.setBalance(1, 100);
        MainTasks.setBalance(2, 100);
        assertEquals(MainTasks.getBalance(1), 100);
        assertEquals(MainTasks.getBalance(2), 100);

        MainTasks.doTransfer(1, 2, 30);

        assertEquals(MainTasks.getBalance(1), 70);
        assertEquals(MainTasks.getBalance(2), 130);


        int[] balances = new int[CLIENTS];
        int sumBefore = 0;
        for (int i = 0; i < CLIENTS; i++) {
            balances[i] = 1000;
            MainTasks.setBalance(i, balances[i]);
            sumBefore += balances[i];
        }


        final int THREADS = 4;
        Thread[] threads = new Thread[THREADS];

        for (int th = 0; th < THREADS; th++) {
            threads[th] = new Thread(new Updater(th + 100), String.format("_Updater #%d", th));
            threads[th].start();
        }
        for (int th = 0; th < THREADS; th++) {
            threads[th].join();
        }


        int sumAfter = 0;
        for (int i = 0; i < CLIENTS; i++) {
            final int balance = MainTasks.getBalance(i);
            sumAfter += balance;
        }

        assertEquals(sumAfter, sumBefore, "Сумма денег на счетах клиентов должна остаться без изменений");

    }

    @Test
    public void testSql() throws Exception {
        List<String> lines = new ArrayList<>();

        File dbStructure = new File(
                URLDecoder.decode(
                        getClass().getClassLoader().getResource("recreate_db.sql").getFile(),
                        StandardCharsets.UTF_8.name()
                )
        );

        try (FileReader reader = new FileReader(dbStructure);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {

                lines.add(line);
            }
        }

        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        //Creating the connection with HSQLDB
        try (Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "")) {
            Statement st = conn.createStatement();

            lines.stream()
                    .filter(this::isValidSQL)
                    .forEach(line -> {
                        try {
                            st.execute(line);
                        } catch (SQLException e) {
                            fail(e.getMessage() + " in line '" + line + "'");
                        }
                    });

            ResultSet rs = st.executeQuery(MainTasks.getSelect());

            assertTrue(rs.next(), "Не вернулось ни одной строки");
            assertEquals(rs.getString(1), "Юридический", "Неправильный отдел");
            assertEquals(rs.getInt(2), 230000, "Неправильная сумма зарплаты");
            assertFalse(rs.next(), "Вернулось более одной строки");
        }

    }

    private boolean isValidSQL(String sqlLine) {
        if (sqlLine.trim().startsWith("--")) return false;
        return !sqlLine.trim().isEmpty();
    }

    static class Updater implements Runnable {

        final int CYCLES = 10_000;

        final Random r;

        Updater(int seed) {
            this.r = new Random(seed);
        }

        @Override
        public void run() {
            for (int cycle = 0; cycle < CYCLES; cycle++) {
                final int amount = r.nextInt(50);
                final int fromClient = r.nextInt(CLIENTS);
                final int toClient = r.nextInt(CLIENTS);

                if (fromClient == toClient) continue;

                MainTasks.doTransfer(fromClient, toClient, amount);
            }
        }
    }

    /**
     * @return короткий список городов для теста на сборку строки через запятую
     */
    private static List<City> getCityListForString() {
        return Arrays.asList(
                MOW,
                TOK,
                SYD,
                SIN
        );
    }

    /**
     * @return список городов для теста на удаление из коллекции
     */
    private static List<City> getCityListForDeletion() {
        return new ArrayList<>(Arrays.asList(
                MOW,
                MEL,
                LEN,
                TOK,
                OSA,
                SYD,
                SIN
        ));
    }

    private static Stream<Country> getCountryStream() {
        return Stream.of(
                new Country("Россия", MOW, LEN),
                new Country("Япония", TOK, OSA),
                new Country("Австралия", SYD, MEL),
                new Country("Сингапур", SIN));
    }
}