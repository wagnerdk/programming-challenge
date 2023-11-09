package de.bcxp.challenge;

import com.opencsv.exceptions.CsvConstraintViolationException;
import de.bcxp.challenge.dataanalysis.WeatherDataAnalyzer;
import de.bcxp.challenge.datareading.ResourceReader;
import de.bcxp.challenge.model.WeatherRecord;
import de.bcxp.challenge.parsing.CsvReader;
import de.bcxp.challenge.parsing.DataParser;
import de.bcxp.challenge.parsing.WeatherRecordVerifier;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemperatureSpreadFromFileIntegrationTest {

    public static final String TEST_FILE_BASE_PATH = "de//bcxp//challenge//weather//";

    @Test
    void temperatureSpreadFromFileTest() throws IOException {
        DataParser<WeatherRecord> weatherRecordReader = new CsvReader<>(',', new WeatherRecordVerifier());
        String csv = ResourceReader.getResource(TEST_FILE_BASE_PATH + "weatherTest.csv");
        List<WeatherRecord> weatherRecords = weatherRecordReader.readToObjects(csv,
                WeatherRecord.class);
        WeatherDataAnalyzer weatherDataAnalyzer = new WeatherDataAnalyzer(weatherRecords);

        assertThat(weatherRecords.size(),  is(30));
        assertThat(weatherDataAnalyzer.findDayWithSmallestSpread().get().getDay(),  is(14));
    }

    @Test
    void temperatureSpreadFromEmptyFileTest() throws IOException {
        DataParser<WeatherRecord> weatherRecordReader = new CsvReader<>(',', new WeatherRecordVerifier());
        String csv = ResourceReader.getResource(TEST_FILE_BASE_PATH + "weatherEmptyTest.csv");
        List<WeatherRecord> weatherRecords = weatherRecordReader.readToObjects(csv, WeatherRecord.class);
        assertThat(weatherRecords, is(empty()));
    }

    @Test
    void temperatureSpreadFromJustHeadingsFileTest() throws IOException {
        DataParser<WeatherRecord> weatherRecordReader = new CsvReader<>(',', new WeatherRecordVerifier());
        String csv = ResourceReader.getResource(TEST_FILE_BASE_PATH + "weatherJustFileHeadings.csv");
        List<WeatherRecord> weatherRecords = weatherRecordReader.readToObjects(csv,
                WeatherRecord.class);
        WeatherDataAnalyzer weatherDataAnalyzer = new WeatherDataAnalyzer(weatherRecords);

        assertThat(weatherRecords.size(),  is(0));
        assertThat(weatherDataAnalyzer.findDayWithSmallestSpread(),  is(Optional.empty()));
    }

    @Test
    void temperatureSpreadWrongInputFileTest() throws IOException {
        DataParser<WeatherRecord> weatherRecordReader = new CsvReader<>(',', new WeatherRecordVerifier());
        String csv = ResourceReader.getResource(TEST_FILE_BASE_PATH + "weatherWrongDataTest.csv");

        Throwable thrown = assertThrows(RuntimeException.class, () -> weatherRecordReader.readToObjects(csv, WeatherRecord.class));
        assertThat(thrown.getCause(), isA(CsvConstraintViolationException.class));
    }
}
