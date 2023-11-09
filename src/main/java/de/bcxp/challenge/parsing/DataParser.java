package de.bcxp.challenge.parsing;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface DataParser<T> {

    /**
     * Reads the {@code input} string and parses it into a list of objects of the provided {@code type}.
     * If the input string is empty, an empty list will be returned.
     * Depending on the implementation it may be necessary to add annotations to the {@code type}
     * to control the deserialization.
     * @see <a href="https://opencsv.sourceforge.net/#annotations_2">Opencsv annotation documentation</a>
     * @param input The string to be parsed
     * @param type The type of the resulting objects.
     * @return A list of the resulting objects.
     * @throws IOException if the inputStream throws an {@link IOException}
     */
    List<T> readToObjects(String input, Class<? extends T> type) throws IOException;
}
