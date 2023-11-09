package de.bcxp.challenge.csvreadertests;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import de.bcxp.challenge.model.Country;
import de.bcxp.challenge.parsing.CountryDataVerifier;
import de.bcxp.challenge.parsing.CsvReader;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvCountryReaderTest {

    @Test
    void simpleCsvInputTest() throws IOException {
        String testCsv = "Name;Population;Area (km²)\n" +
                         "Austria;8926000;83855\n" +
                         "Belgium;11566041;30528";
        CsvReader<Country> testDataCsvReader = new CsvReader<>(';');
        List<Country> testDataList = testDataCsvReader.readToObjects(testCsv, Country.class);

        assertThat(testDataList, hasSize(2));
        assertThat(testDataList.get(0).getName(), is("Austria"));
        assertThat(testDataList.get(0).getPopulation(), is(8926000));
        assertThat(testDataList.get(0).getArea(), is(83855));

        assertThat(testDataList.get(1).getName(), is("Belgium"));
        assertThat(testDataList.get(1).getPopulation(), is(11566041));
        assertThat(testDataList.get(1).getArea(), is(30528));
    }

    @Test
    void noCountryNameCsvInputTest() {
        String testCsv = "Name;Population;Area (km²)\n" +
                ";-1;83855\n";
        CsvReader<Country> testDataCsvReader = new CsvReader<>(';');

        Throwable thrown = assertThrows(RuntimeException.class, () -> testDataCsvReader.readToObjects(testCsv, Country.class));
        assertThat(thrown.getCause(), isA(CsvRequiredFieldEmptyException.class));
    }

    @Test
    void negativePopulationCsvInputTest() {
        String testCsv = "Name;Population;Area (km²)\n" +
                "Austria;-1;83855\n";
        CsvReader<Country> testDataCsvReader = new CsvReader<>(';',new CountryDataVerifier());

        Throwable thrown = assertThrows(RuntimeException.class, () -> testDataCsvReader.readToObjects(testCsv, Country.class));
        assertThat(thrown.getCause(), isA(CsvConstraintViolationException.class));
    }

    @Test
    void negativeAreaCsvInputTest() {
        String testCsv = "Name;Population;Area (km²)\n" +
                "Austria;8926000;-1\n";
        CsvReader<Country> testDataCsvReader = new CsvReader<>(';',new CountryDataVerifier());

        Throwable thrown = assertThrows(RuntimeException.class, () -> testDataCsvReader.readToObjects(testCsv, Country.class));
        assertThat(thrown.getCause(), isA(CsvConstraintViolationException.class));
    }
}
