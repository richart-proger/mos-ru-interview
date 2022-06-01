package ru.mos.interviewjava;

/**
 * Город
 */
@SuppressWarnings("unused")
class City {
    /**
     * Название
     */
    private final String name;
    /**
     * Население
     */
    private final int population;

    /**
     * @param name       название города
     * @param population население города
     */
    City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
