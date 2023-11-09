package de.bcxp.challenge.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

/**
 * Represents a Country with its name, population, and area information.
 */
public class Country {

    /**
     * The name of the country.
     */
    @CsvBindByName(column = "Name", required = true)
    private String name;

    /**
     * The population count of the country.
     */
    @CsvBindByName(column = "Population", required = true, locale = "de-DE")
    private int population;

    /**
     * The area of the country in square kilometers.
     */
    @CsvBindByName(column = "Area (km²)", required = true)
    private int area;

    public Country(String name, int population, int area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    /**
     * Constructor for opencsv
     */
    public  Country(){}

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", area=" + area +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return getPopulation() == country.getPopulation() && getArea() == country.getArea() && Objects.equals(getName(), country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPopulation(), getArea());
    }
}
