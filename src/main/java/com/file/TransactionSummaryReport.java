package com.file;

import com.file.model.TransactionSummary;
import com.file.parser.FileOperationsService;
import com.file.parser.FileOperationsServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TransactionSummaryReport {
    public static void main(String[] args) {
    	Logger logger = Logger.getLogger(TransactionSummaryReport.class);
    	PropertyConfigurator.configure("log4j.properties");
    	Properties properties = PropertyConfigurationReader.getInstance();
        try {
            FileOperationsService parser = new FileOperationsServiceImpl();
            Set<TransactionSummary> transactionSummaryList = parser.readAndProcessFile(properties.getProperty("input-fileName"));
            parser.generateTransactionSummaryReportFile(transactionSummaryList);

        } catch (Exception e) {
            logger.error("Failed to read and generate the summary report" + e.getMessage(), e);
        }
    }
}