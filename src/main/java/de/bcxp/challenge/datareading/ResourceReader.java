package de.bcxp.challenge.datareading;

import de.bcxp.challenge.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceReader {

    final static Logger logger = LoggerFactory.getLogger(ResourceReader.class);

    /**
     * Retrieves the content of a resource  as a UTF-8 String.
     *
     * @param path The path to the resource to be read.
     * @return The content of the resource as a String, encoded in UTF-8.
     * @throws IOException If there is an error while reading the resource.
     * @throws CouldNotReadResourceException if the resource could not be found,
     *                                       the resource is in a package that is not opened unconditionally,
     *                                       or access to the resource is denied by the security manager.
     * @see ClassLoader#getResourceAsStream(String) 
     */
    public static String getResource(String path) throws IOException {
        logger.atDebug().log("Reading the path '{}' from resources", path);
        InputStream resourceInputSteam = App.class.getClassLoader().getResourceAsStream(path);
        if(resourceInputSteam != null){
            return new String(resourceInputSteam.readAllBytes(), StandardCharsets.UTF_8);
        }else{
            throw new CouldNotReadResourceException("The path " + path + " could not be read");
        }
    }
}
