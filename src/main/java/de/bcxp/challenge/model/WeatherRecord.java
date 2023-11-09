package de.bcxp.challenge.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

/**
 * Represents a weather record for a specific day,
 * including day number, maximum temperature, and minimum temperature.
 */
public class WeatherRecord {

    /**
     * The number of the day in a month.
     */
    @CsvBindByName(column = "Day", required = true)
    private int day;

    /**
     * The highest temperature in degree Fahrenheit of the day.
     */
    @CsvBindByName(column = "MxT", required = true)
    private int maximumTemperature;

    /**
     * The lowest temperature in degree Fahrenheit of the day.
     */
    @CsvBindByName(column = "MnT", required = true)
    private int minimumTemperature;

    public WeatherRecord(int day, int maximumTemperature, int minimumTemperature) {
        this.day = day;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
    }

    /**
     * Constructor for opencsv
     */
    public WeatherRecord(){}

    public int getDay() {
        return day;
    }

    public int getMaximumTemperature() {
        return maximumTemperature;
    }

    public int getMinimumTemperature() {
        return minimumTemperature;
    }

    @Override
    public String toString() {
        return "WeatherRecord{" +
                "day=" + day +
                ", maximumTemperature=" + maximumTemperature +
                ", minimumTemperature=" + minimumTemperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherRecord that = (WeatherRecord) o;
        return getDay() == that.getDay() && getMaximumTemperature() == that.getMaximumTemperature() && getMinimumTemperature() == that.getMinimumTemperature();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getMaximumTemperature(), getMinimumTemperature());
    }
}
