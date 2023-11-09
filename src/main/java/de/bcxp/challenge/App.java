package de.bcxp.challenge;

import de.bcxp.challenge.dataanalysis.CountryDataAnalyzer;
import de.bcxp.challenge.dataanalysis.WeatherDataAnalyzer;
import de.bcxp.challenge.datareading.ResourceReader;
import de.bcxp.challenge.model.Country;
import de.bcxp.challenge.model.WeatherRecord;
import de.bcxp.challenge.parsing.CountryDataVerifier;
import de.bcxp.challenge.parsing.CsvReader;
import de.bcxp.challenge.parsing.DataParser;
import de.bcxp.challenge.parsing.WeatherRecordVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    final static Logger logger = LoggerFactory.getLogger(WeatherRecordVerifier.class);

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        try {
            // Your preparation code …
            DataParser<WeatherRecord> weatherRecordReader = new CsvReader<>(',', new WeatherRecordVerifier());
            List<WeatherRecord> weatherRecords = weatherRecordReader.readToObjects(ResourceReader.getResource("de//bcxp//challenge//weather.csv"),
                    WeatherRecord.class);
            WeatherDataAnalyzer weatherDataAnalyzer = new WeatherDataAnalyzer(weatherRecords);

            int dayWithSmallestTempSpread = weatherDataAnalyzer.findDayWithSmallestSpread().get().getDay();     // Your day analysis function call …
            System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);


            DataParser<Country> countryRecordCsvReader = new CsvReader<>(';', new CountryDataVerifier());
            List<Country> countries = countryRecordCsvReader.readToObjects(ResourceReader.getResource("de//bcxp//challenge//countries.csv"),
                    Country.class);
            CountryDataAnalyzer countryDataAnalyzer = new CountryDataAnalyzer(countries);

            String countryWithHighestPopulationDensity = countryDataAnalyzer.findCountryWithHighestPopulationDensity().get().getName(); // Your population density analysis function call …
            System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);

        } catch (IOException e) {
            logger.atError().log("An IOException occurred", e);
        }
    }
}
