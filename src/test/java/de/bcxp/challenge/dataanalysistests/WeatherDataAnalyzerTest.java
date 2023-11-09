package de.bcxp.challenge.dataanalysistests;

import de.bcxp.challenge.dataanalysis.WeatherDataAnalyzer;
import de.bcxp.challenge.model.WeatherRecord;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WeatherDataAnalyzerTest {

    public static final int MIN_TEMPERATURE = 10;
    public static final int MAX_TEMPERATURE = 20;

    @Test
    void temperatureSpreadTest() {
        WeatherRecord weatherRecord = new WeatherRecord(1, MAX_TEMPERATURE, MIN_TEMPERATURE);
        assertThat(WeatherDataAnalyzer.getTemperatureSpread(weatherRecord), is(MAX_TEMPERATURE - MIN_TEMPERATURE));
    }

    @Test
    void zeroTemperatureSpreadTest() {
        WeatherRecord weatherRecord = new WeatherRecord(1, MAX_TEMPERATURE, MAX_TEMPERATURE);
        assertThat(WeatherDataAnalyzer.getTemperatureSpread(weatherRecord), is(0));
    }

    @Test
    void validMinSpreadCalculationTest() {
        List<WeatherRecord> weatherRecordList = new LinkedList<>();
        int spreadDifference = 5;
        WeatherRecord weatherRecordWithBiggerSpread = new WeatherRecord(1, MAX_TEMPERATURE, MIN_TEMPERATURE);
        WeatherRecord weatherRecordWithSmallerSpread = new WeatherRecord(2, MAX_TEMPERATURE - spreadDifference, MIN_TEMPERATURE);
        weatherRecordList.add(weatherRecordWithBiggerSpread);
        weatherRecordList.add(weatherRecordWithSmallerSpread);
        WeatherDataAnalyzer weatherDataAnalyzer = new WeatherDataAnalyzer(weatherRecordList);

        assertThat(weatherDataAnalyzer.findDayWithSmallestSpread().get(), is(weatherRecordWithSmallerSpread));
    }

    @Test
    void noDataSpreadCalculationTest() {
        WeatherDataAnalyzer weatherDataAnalyzer = new WeatherDataAnalyzer(Collections.emptyList());
        assertThat(weatherDataAnalyzer.findDayWithSmallestSpread(), is(Optional.empty()));
    }

    @Test
    void singleDataSpreadCalculationTest() {
        WeatherRecord weatherRecord = new WeatherRecord(2, MAX_TEMPERATURE, MIN_TEMPERATURE);
        WeatherDataAnalyzer weatherDataAnalyzer = new WeatherDataAnalyzer(Collections.singletonList(weatherRecord));
        assertThat(weatherDataAnalyzer.findDayWithSmallestSpread().get(), is(weatherRecord));
    }
}
