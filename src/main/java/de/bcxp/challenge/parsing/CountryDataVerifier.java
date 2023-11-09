package de.bcxp.challenge.parsing;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import de.bcxp.challenge.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountryDataVerifier implements BeanVerifier<Country> {

    final static Logger logger = LoggerFactory.getLogger(WeatherRecordVerifier.class);

    /**
     * Verifies the following requirements for each country object:
     *
     * <p><ul>
     * <li>The Country must have a non-null and non-empty name.
     * <li>The area of the Country must not be negative.
     * <li>The population of the Country must not be negative.
     * </ul><p>
     * The method throws an exception and does not only return false to prevent OpenCSV from filtering the record silently.
     * @param country The country object to be verified.
     * @return true if the Country object passes all constraints.
     * @throws CsvConstraintViolationException if any of the specified constraints are violated.
     * @see BeanVerifier#verifyBean(Object)
     */
    @Override
    public boolean verifyBean(Country country) throws CsvConstraintViolationException {
        logger.atDebug().log("Checking data of country {}", country.getName());

        if(country.getName() == null || country.getName().isEmpty()){
            throw new CsvConstraintViolationException(country, "Each country needs a name");
        }
        if(country.getArea() < 0){
            throw new CsvConstraintViolationException(country, "The area of " + country.getName() + " can not be negative");
        }

        if(country.getPopulation() < 0){
            throw new CsvConstraintViolationException(country, "The population of " + country.getName() + " can not be negative");
        }

        return true;
    }
}
