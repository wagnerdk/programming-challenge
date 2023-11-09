package de.bcxp.challenge.datareading;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResourceReaderTest {

    public static final String TEST_FILE_BASE_PATH = "de//bcxp//challenge//resourcereader//";

    @Test
    void validInputFileTest() throws IOException {
        String testData = ResourceReader.getResource(TEST_FILE_BASE_PATH + "ResourceReaderTestFile.txt");
        assertThat(testData, is("Test"));
    }

    @Test
    void emptyFileTest() throws IOException {
        String testData = ResourceReader.getResource(TEST_FILE_BASE_PATH + "ResourceReaderTestEmptyFile.txt");
        assertThat(testData, is(""));
    }

    @Test
    void noFileTest() {
        assertThrows(CouldNotReadResourceException.class,
                () -> ResourceReader.getResource(TEST_FILE_BASE_PATH + "INVALID"));
    }
}
