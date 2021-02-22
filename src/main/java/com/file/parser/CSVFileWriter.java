package com.file.parser;

import com.file.PropertyConfigurationReader;
import com.file.model.TransactionSummary;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.Properties;
import java.util.Set;

public class CSVFileWriter {
    static Logger logger = Logger.getLogger(CSVFileWriter.class);
    Properties properties = PropertyConfigurationReader.getInstance();
    private String[] getHeader() {
        return properties.getProperty("output_file_header").split(",");
    }

    public void writeToCSV(Set<TransactionSummary> transactionSummaryList) throws IOException {
        try {
            FileWriter csvWriter = new FileWriter(properties.getProperty("output_file_name"));
            CSVPrinter csvPrinter = new CSVPrinter(csvWriter, CSVFormat.DEFAULT.withHeader(getHeader()));
            for (TransactionSummary transactionSummary : transactionSummaryList) {
                csvPrinter.printRecord(transactionSummary.getClientInformation(), transactionSummary.getProductInformation(), transactionSummary.getTotalTransactionAmount());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            logger.error("Failed to write into CSV file " + e.getMessage() ,e);
            throw new IOException("Failed to write into CSV file " + e.getMessage() ,e);
        }
    }
}
