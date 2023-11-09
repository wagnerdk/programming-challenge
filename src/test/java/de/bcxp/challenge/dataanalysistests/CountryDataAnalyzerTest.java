package de.bcxp.challenge.dataanalysistests;

import de.bcxp.challenge.dataanalysis.CountryDataAnalyzer;
import de.bcxp.challenge.model.Country;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class CountryDataAnalyzerTest {

    @Test
    void populationDensityCalculationTest() {
        int population = 8926000;
        int area = 83855;
        Country country = new Country("Astria", population, area);
        double populationDensity = (double) population / (double) area;
        assertThat(CountryDataAnalyzer.calculatePopulationDensity(country),
                closeTo(populationDensity, 0.001)); //Doubles can be a bit off because of IEEE 754 representation
    }

    @Test
    void zeroPopulationDensityCalculationTest() {
        int population = 0;
        int area = 1;
        Country country = new Country("Astria", population, area);
        double populationDensity = (double) population / (double) area;
        assertThat(CountryDataAnalyzer.calculatePopulationDensity(country),
                closeTo(populationDensity, 0.001));
    }

    @Test
    void highestPopulationDensityFinderTest() {
        Country countryWithHighestPopulationDensity = new Country("Malta", 516100, 316);
        List<Country> countryList = new LinkedList<>();
        countryList.add(new Country("Austria",8926000,83855));
        countryList.add(new Country("Belgium",11566041,30528));
        countryList.add(new Country("Croatia",4036355,56594));
        countryList.add(countryWithHighestPopulationDensity);
        CountryDataAnalyzer countryDataAnalyzer = new CountryDataAnalyzer(countryList);

        assertThat(countryDataAnalyzer.findCountryWithHighestPopulationDensity().get(), is(countryWithHighestPopulationDensity));
    }

    @Test
    void noDataCountryTest() {
        CountryDataAnalyzer countryDataAnalyzer = new CountryDataAnalyzer(Collections.emptyList());
        assertThat(countryDataAnalyzer.findCountryWithHighestPopulationDensity(), is(Optional.empty()));
    }

    @Test
    void singleDataCountryTest() {
        Country country = new Country("Malta", 516100, 316);
        CountryDataAnalyzer countryDataAnalyzer = new CountryDataAnalyzer(Collections.singletonList(country));
        assertThat(countryDataAnalyzer.findCountryWithHighestPopulationDensity().get(), is(country));
    }

}
