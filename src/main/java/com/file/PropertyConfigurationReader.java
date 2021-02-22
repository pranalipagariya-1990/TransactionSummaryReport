package com.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyConfigurationReader {
    public static Properties instance = new Properties();
    static Logger logger = Logger.getLogger(PropertyConfigurationReader.class);
    public static Properties getInstance() {
        try (InputStream input = new FileInputStream("input.properties")) {
            instance.load(input);
        } catch (FileNotFoundException e ) {
            logger.error("Property File Not found " + e.getMessage() ,e);
        } catch (IOException e) {
            logger.error("Error in reading properties from file " + e.getMessage() ,e);
        }
        return instance;
    }
}