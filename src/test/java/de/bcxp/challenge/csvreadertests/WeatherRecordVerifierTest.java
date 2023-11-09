package de.bcxp.challenge.csvreadertests;

import com.opencsv.exceptions.CsvConstraintViolationException;
import de.bcxp.challenge.model.WeatherRecord;
import de.bcxp.challenge.parsing.WeatherRecordVerifier;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherRecordVerifierTest {

    public static final int MIN_TEMPERATURE = 10;
    public static final int MAX_TEMPERATURE = 20;

    @Test
    void validTemperatureTest() throws CsvConstraintViolationException {
        WeatherRecord weatherRecord = new WeatherRecord(1, MAX_TEMPERATURE, MIN_TEMPERATURE);
        WeatherRecordVerifier weatherRecordVerifier = new WeatherRecordVerifier();
        assertThat(weatherRecordVerifier.verifyBean(weatherRecord),  is(true));
    }

    @Test
    void maxBelowMinTemperatureTest() {
        WeatherRecord weatherRecord = new WeatherRecord(1, MIN_TEMPERATURE, MAX_TEMPERATURE);
        WeatherRecordVerifier weatherRecordVerifier = new WeatherRecordVerifier();
        assertThrows(CsvConstraintViolationException.class, () -> weatherRecordVerifier.verifyBean(weatherRecord));
    }

    @Test
    void negativeDayTest() {
        WeatherRecord weatherRecord = new WeatherRecord(-2, MAX_TEMPERATURE, MIN_TEMPERATURE);
        WeatherRecordVerifier weatherRecordVerifier = new WeatherRecordVerifier();
        assertThrows(CsvConstraintViolationException.class, () -> weatherRecordVerifier.verifyBean(weatherRecord));
    }
}
