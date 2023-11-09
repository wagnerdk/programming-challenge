package de.bcxp.challenge;

import com.opencsv.exceptions.CsvConstraintViolationException;
import de.bcxp.challenge.dataanalysis.CountryDataAnalyzer;
import de.bcxp.challenge.datareading.ResourceReader;
import de.bcxp.challenge.model.Country;
import de.bcxp.challenge.parsing.CountryDataVerifier;
import de.bcxp.challenge.parsing.CsvReader;
import de.bcxp.challenge.parsing.DataParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountriesPopulationDensityIntegrationTest {

    public static final String TEST_FILE_BASE_PATH = "de//bcxp//challenge//country//";

    @Test
    void countriesFromFileTest() throws IOException {
        DataParser<Country> countryDataReader = new CsvReader<>(';', new CountryDataVerifier());
        String csv = ResourceReader.getResource(TEST_FILE_BASE_PATH + "countriesTest.csv");
        List<Country> countriesList = countryDataReader.readToObjects(csv,
                Country.class);
        CountryDataAnalyzer countryDataAnalyzer = new CountryDataAnalyzer(countriesList);
        assertThat(countriesList.size(),  is(27));
        assertThat(countryDataAnalyzer.findCountryWithHighestPopulationDensity().get().getName(),  is("Malta"));
    }

    @Test
    void negativePopulationTest() throws IOException {
        DataParser<Country> countryDataReader = new CsvReader<>(';', new CountryDataVerifier());
        String csv = ResourceReader.getResource(TEST_FILE_BASE_PATH + "countriesNegativPopulationTest.csv");
        Throwable thrown = assertThrows(RuntimeException.class, () -> countryDataReader.readToObjects(csv, Country.class));
        assertThat(thrown.getCause(), isA(CsvConstraintViolationException.class));
    }
}
