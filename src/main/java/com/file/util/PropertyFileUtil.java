package com.file.util;

import com.file.PropertyConfigurationReader;
import com.file.parser.FileOperationsServiceImpl;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
    private final static Properties properties = PropertyConfigurationReader.getInstance();
    static Logger logger = Logger.getLogger(FileOperationsServiceImpl.class);
    public static String getCombinedValueForSummaryReportColumn(String propertyName, String line) throws IOException {
        String[] transactionFileFields = properties.getProperty(propertyName).split(",");
        StringBuilder combinedFieldsValue = new StringBuilder();
        try{
            for (String fields : transactionFileFields) {
                String[] toAndFrom = properties.getProperty(fields).split("-");
                combinedFieldsValue.append(line, Integer.parseInt(toAndFrom[0]), Integer.parseInt(toAndFrom[1]));
            }
        }catch (IndexOutOfBoundsException e){
            logger.error("File data incorrect -> " + line);
            //TODO can write the incorrect records to a file
            throw new IOException("File data incorrect at line " + e.getMessage() , e);
        }
        return combinedFieldsValue.toString();
    }

}
