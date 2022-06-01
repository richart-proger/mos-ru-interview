package ru.mos.interviewjava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Страна
 */
@SuppressWarnings("unused")
class Country {
    /**
     * Список городов страны
     */
    private final List<City> cities;
    /**
     * Название страны
     */
    private final String name;

    /**
     * Конструктор
     *
     * @param name   название страны
     * @param cities список городов этой страны
     */
    public Country(String name, City... cities) {
        this.name = name;
        this.cities = Arrays.asList(cities);
    }

    public List<City> getCities() {
        return cities;
    }

    /**
     * @return Поток городов страны
     */
    public Stream<City> getCityStream() {
        return cities.stream();
    }


    public String getName() {
        return name;
    }
}
