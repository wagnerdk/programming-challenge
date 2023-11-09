package de.bcxp.challenge.csvreadertests;

import com.opencsv.exceptions.CsvConstraintViolationException;
import de.bcxp.challenge.model.Country;
import de.bcxp.challenge.parsing.CountryDataVerifier;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountryDataVerifierTest {

    @Test
    void validCountryTest() throws CsvConstraintViolationException {
        Country validCountry = new Country("Austria",8926000, 83855);
        CountryDataVerifier countryDataVerifier = new CountryDataVerifier();
        assertThat(countryDataVerifier.verifyBean(validCountry),  is(true));
    }

    @Test
    void emptyNameCountryTest() {
        Country noNameCountry = new Country("",8926000, 83855);
        CountryDataVerifier countryDataVerifier = new CountryDataVerifier();
        assertThrows(CsvConstraintViolationException.class, () -> countryDataVerifier.verifyBean(noNameCountry));
    }

    @Test
    void negativePopulationTest() {
        Country negativePopulationCountry = new Country("Austria",-1, 83855);
        CountryDataVerifier countryDataVerifier = new CountryDataVerifier();
        assertThrows(CsvConstraintViolationException.class, () -> countryDataVerifier.verifyBean(negativePopulationCountry));
    }

    @Test
    void negativeAreaTest() {
        Country negativePopulationCountry = new Country("Austria",8926000, -1);
        CountryDataVerifier countryDataVerifier = new CountryDataVerifier();
        assertThrows(CsvConstraintViolationException.class, () -> countryDataVerifier.verifyBean(negativePopulationCountry));
    }
}
