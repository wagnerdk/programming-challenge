package de.bcxp.challenge.parsing;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The CsvReader converts csv data to a list of new instances of <T>.
 * @param <T> The type of the resulting objects.
 */
public class CsvReader<T> implements DataParser<T>{
    final static Logger logger = LoggerFactory.getLogger(CsvReader.class);

    private CSVParser parser;
    private BeanVerifier<T> beanVerifier;

    /**
     * Constructs a csv reader to convert csv files to instances of T.
     * @param csvSeparator the separator character which is used to delimit the different fields.
     */
    public CsvReader(char csvSeparator){
        logger.atInfo().log("Using '{}' as csv separator", csvSeparator);
        this.parser = new CSVParserBuilder()
                .withSeparator(csvSeparator)
                .build();
    }

    /**
     * Constructs a csv reader to convert csv files to instances of T.
     *
     * @param csvSeparator the separator character which is used to delimit the different fields.
     * @param beanVerifier the verifier which is applied to each read record. Can be null.
     * @see BeanVerifier
     */
    public CsvReader(char csvSeparator, BeanVerifier<T> beanVerifier){
        this(csvSeparator);
        this.beanVerifier = beanVerifier;
    }
    public List<T> readToObjects(String input, Class<? extends T> type) throws IOException {
        Objects.requireNonNull(input, "The input must be non null");
        Objects.requireNonNull(type, "The type must be non null");
        if(input.isEmpty()){
            return Collections.emptyList();
        }
        try (CSVReader csvReader = new CSVReaderBuilder(new StringReader(input))
                     .withCSVParser(parser)
                     .build()) {
            CsvToBean<T> csvToBeanConverter = new CsvToBeanBuilder<T>(csvReader)
                    .withType(type)
                    .withVerifier(beanVerifier)
                    .build();
            List<T> objectList = csvToBeanConverter.parse();
            logger.atInfo().log("Read '{}' csv entries to objects of type '{}'", objectList.size(), type);
            return objectList;
        }
    }
}
