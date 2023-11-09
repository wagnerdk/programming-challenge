package de.bcxp.challenge.dataanalysis;

import de.bcxp.challenge.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CountryDataAnalyzer {
    final static Logger logger = LoggerFactory.getLogger(CountryDataAnalyzer.class);
    private List<Country> countries;
    public CountryDataAnalyzer(List<Country> countries){
        Objects.requireNonNull(countries, "The countries list must be non null");
        this.countries = countries;
    }

    /**
     * Finds the {@link Country} with the highest population density among the countries.
     * Population density is calculated as the ratio of population to area.
     *
     * If the list of countries is empty an empty Optional is returned.
     *
     * @return An Optional containing the Country with the highest population density, or an empty Optional
     *         if the list of countries is empty.
     */
    public Optional<Country> findCountryWithHighestPopulationDensity(){
        if(countries.size() == 0){
            logger.atWarn().log("Could not find the country with highest population density because the country list is empty");
            return Optional.empty();
        }
        double highestPopulationDensity = Double.MIN_VALUE;
        Country highestPopulatedCountry = null;
        for(Country country : countries){
            double populationDensity = calculatePopulationDensity(country);
            if(populationDensity > highestPopulationDensity){
                highestPopulationDensity = populationDensity;
                highestPopulatedCountry = country;
            }
        }
        return Optional.of(highestPopulatedCountry);
    }

    /**
     * Calculates the population density of a given country based on its population and area.
     * The population density is calculated as the ratio of the population to the area.
     *
     * @param country The country for which population density should be calculated.
     * @return The population density of the country.
     */
    public static double calculatePopulationDensity(Country country){
        return (double) country.getPopulation() / (double) country.getArea();
    }
}
