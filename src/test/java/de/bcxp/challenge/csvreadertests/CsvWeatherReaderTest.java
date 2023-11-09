package de.bcxp.challenge.csvreadertests;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import de.bcxp.challenge.model.WeatherRecord;
import de.bcxp.challenge.parsing.CsvReader;
import de.bcxp.challenge.parsing.WeatherRecordVerifier;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvWeatherReaderTest {

    @Test
    void emptyCsvInputTest() throws IOException {
        String testCsv = "";
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(',');

        List<WeatherRecord> weatherRecords = testDataCsvReader.readToObjects(testCsv, WeatherRecord.class);
        assertThat(weatherRecords, is(empty()));
    }

    @Test
    void invalidCsvInputTest() throws IOException {
        String testCsv = "Day,MxT,MnT" + "\n" +
                "1,88,59,INVALID";
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(',');
        Throwable thrown = assertThrows(RuntimeException.class, () -> testDataCsvReader.readToObjects(testCsv, WeatherRecord.class));
        assertThat(thrown.getCause(), isA(CsvRequiredFieldEmptyException.class));
    }

    @Test
    void nullReaderInputTest() {
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(',');
        Throwable thrown = assertThrows(NullPointerException.class, () -> testDataCsvReader.readToObjects(null, WeatherRecord.class));
        assertThat(thrown, isA(NullPointerException.class));
    }

    @Test
    void simpleCsvInputTest() throws IOException {
        String testCsv = "Day,MxT,MnT" + "\n" +
                "1,88,59" + "\n" +
                "2,79,63";
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(',');
        List<WeatherRecord> testDataList = testDataCsvReader.readToObjects(testCsv, WeatherRecord.class);

        assertThat(testDataList, hasSize(2));
        assertThat(testDataList.get(0).getDay(), is(1));
        assertThat(testDataList.get(0).getMaximumTemperature(), is(88));
        assertThat(testDataList.get(0).getMinimumTemperature(), is(59));

        assertThat(testDataList.get(1).getDay(), is(2));
        assertThat(testDataList.get(1).getMaximumTemperature(), is(79));
        assertThat(testDataList.get(1).getMinimumTemperature(), is(63));
    }

    @Test
    void differentSeparatorCsvInputTest() throws IOException {
        String testCsv = "Day;MxT;MnT" + "\n" +
                "1;88;59";
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(';');
        List<WeatherRecord> testDataList = testDataCsvReader.readToObjects(testCsv, WeatherRecord.class);

        assertThat(testDataList, hasSize(1));
        assertThat(testDataList.get(0).getDay(), is(1));
        assertThat(testDataList.get(0).getMaximumTemperature(), is(88));
        assertThat(testDataList.get(0).getMinimumTemperature(), is(59));
    }

    @Test
    void notaNumberCsvInputTest() throws IOException {
        String testCsv = "Day;MxT;MnT" + "\n" +
                "NOTaNUMBER;88;59";
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(';');

        Throwable thrown = assertThrows(RuntimeException.class, () -> testDataCsvReader.readToObjects(testCsv, WeatherRecord.class));
        assertThat(thrown.getCause(), isA(CsvDataTypeMismatchException.class));
    }

    @Test
    void moreFieldsCsvInputTest() throws IOException {
        String testCsv = "Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP\n" +
                         "1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5";
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(',');

        List<WeatherRecord> testDataList = testDataCsvReader.readToObjects(testCsv, WeatherRecord.class);

        assertThat(testDataList, hasSize(1));
        assertThat(testDataList.get(0).getDay(), is(1));
        assertThat(testDataList.get(0).getMaximumTemperature(), is(88));
        assertThat(testDataList.get(0).getMinimumTemperature(), is(59));
    }

    @Test
    void temperatureValueVerifierTest() throws IOException {
        String testCsv = "Day,MxT,MnT" + "\n" +
                "1,28,59";
        CsvReader<WeatherRecord> testDataCsvReader = new CsvReader<>(',', new WeatherRecordVerifier());

        Throwable thrown = assertThrows(RuntimeException.class, () -> testDataCsvReader.readToObjects(testCsv, WeatherRecord.class));
        assertThat(thrown.getCause(), isA(CsvConstraintViolationException.class));
    }

}
