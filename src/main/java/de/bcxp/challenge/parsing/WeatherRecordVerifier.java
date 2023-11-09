package de.bcxp.challenge.parsing;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import de.bcxp.challenge.model.WeatherRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifies that the minimum temperature is below or equal the maximal temperature.
 */
public class WeatherRecordVerifier implements BeanVerifier<WeatherRecord> {

    final static Logger logger = LoggerFactory.getLogger(WeatherRecordVerifier.class);

    /**
     * Verifies a WeatherRecord object to ensure that the maximum temperature is not above the minimum temperature
     * and that the day is not negative.
     * The method throws an exception and does not only return false to prevent OpenCSV to filter the record silently.
     * @param weatherRecord The record to be verified.
     * @return true if the verification is successful, indicating that the WeatherRecord is valid.
     * @throws CsvConstraintViolationException thrown if the minimum temperature is above the maximum temperature
     *                                         or if the day is negative.
     * @see BeanVerifier#verifyBean(Object) 
     */
    @Override
    public boolean verifyBean(WeatherRecord weatherRecord) throws CsvConstraintViolationException {
        logger.atDebug().log("Verifying the weather record object of day {}", weatherRecord.getDay());

        if(weatherRecord.getMinimumTemperature() > weatherRecord.getMaximumTemperature()){
            throw new CsvConstraintViolationException(weatherRecord, "The maximum temperature of day " + weatherRecord.getDay() +
                    " is above the minimum temperature");
        }
        if(weatherRecord.getDay() < 0){
            throw new CsvConstraintViolationException(weatherRecord, "The day ' + " + weatherRecord.getDay() + "' must not be negative");
        }

        return true;
    }
}
