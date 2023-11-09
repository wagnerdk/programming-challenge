package de.bcxp.challenge.dataanalysis;

import de.bcxp.challenge.model.WeatherRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WeatherDataAnalyzer {

    final static Logger logger = LoggerFactory.getLogger(WeatherDataAnalyzer.class);

    private List<WeatherRecord> weatherRecords;

    public WeatherDataAnalyzer(List<WeatherRecord> weatherRecords){
        Objects.requireNonNull(weatherRecords, "The weather record list must be non null");
        this.weatherRecords = weatherRecords;
    }

    /**
     * Finds the {@link WeatherRecord} with the smallest temperature spread for the day.
     * The temperature spread is calculated as the difference between the maximum temperature
     * and the minimum temperature.
     * @return An {@link Optional} containing the {@link WeatherRecord} with the smallest temperature spread,
     *         or an empty {@link Optional} if the list of weather records is empty.
     */
    public Optional<WeatherRecord> findDayWithSmallestSpread(){
        if(weatherRecords.size() == 0){
            logger.atWarn().log("Could not find the weather record with the smallest temperature spread because the record list is empty");
            return Optional.empty();
        }
        int smallestSpread = Integer.MAX_VALUE;
        WeatherRecord smallestSpreadRecord = null;
        for(WeatherRecord weatherRecord : weatherRecords){
            int temperatureSpread = getTemperatureSpread(weatherRecord);
            if(temperatureSpread < smallestSpread){
                smallestSpread = temperatureSpread;
                smallestSpreadRecord = weatherRecord;
            }
        }
        return Optional.of(smallestSpreadRecord);
    }

    /**
     * Calculates the temperature spread for a {@link WeatherRecord} object.
     * The temperature spread is defined as the difference between the maximum temperature
     * and the minimum temperature.
     *
     * @param weatherRecord The WeatherRecord object for which the temperature spread
     *                     needs to be calculated.
     * @return The temperature spread.
     */
    public static int getTemperatureSpread(WeatherRecord weatherRecord) {
        return weatherRecord.getMaximumTemperature() - weatherRecord.getMinimumTemperature();
    }
}
