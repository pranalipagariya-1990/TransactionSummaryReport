package com.file.parser;

import com.file.PropertyConfigurationReader;
import com.file.model.TransactionSummary;
import com.file.util.PropertyFileUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class FileOperationsServiceImpl implements FileOperationsService {

    private final Properties properties = PropertyConfigurationReader.getInstance();
    static Logger logger = Logger.getLogger(FileOperationsServiceImpl.class);

    @Override
    public Set<TransactionSummary> readAndProcessFile(String fileName) throws IOException {
        Set<TransactionSummary> transactionSummaryList = new HashSet<TransactionSummary>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach(line -> {
                try {
                    processFileData(transactionSummaryList, line);
                } catch (IOException e) {
                    logger.error("Failed to process file data " + e.getMessage() ,e);
                }
            });
        } catch (IOException e) {
            logger.error("Failed to read and process file " + e.getMessage() ,e);
            throw new IOException("Failed to read and process file " + e.getMessage() ,e);
        }
        return transactionSummaryList;
    }

    @Override
    public boolean generateTransactionSummaryReportFile(Set<TransactionSummary> transactionSummaryList) throws IOException {
          CSVFileWriter csvFileWriter = new CSVFileWriter();
        try {
            logger.info("Generating the transaction summary report");
            csvFileWriter.writeToCSV(transactionSummaryList);
            return true;
        } catch (IOException e) {
            logger.error("Failed to write into CSV file " + e.getMessage() ,e);
            throw new IOException("Failed to write into CSV file " + e.getMessage() ,e);
        }
    }

    private void processFileData(Set<TransactionSummary> transactionSummaryList, String line) throws IOException {
        TransactionSummary transactionSummary = generateTransactionSummaryReportData(line);
        Optional<TransactionSummary> transactionRow = transactionSummaryList.stream().filter(transactionSummary::equals).findFirst();
        if (transactionRow.isPresent()) {
            transactionRow.get().setTotalTransactionAmount(transactionRow.get().getTotalTransactionAmount() + transactionSummary.getTotalTransactionAmount());
            transactionSummaryList.add(transactionRow.get());
        } else {
            transactionSummaryList.add(transactionSummary);
        }
    }

    private TransactionSummary generateTransactionSummaryReportData(String line) throws IOException {
        TransactionSummary transactionSummary = new TransactionSummary();
        transactionSummary.setClientInformation(PropertyFileUtil.getCombinedValueForSummaryReportColumn("clientInformation", line));
        transactionSummary.setProductInformation(PropertyFileUtil.getCombinedValueForSummaryReportColumn("productInformation", line));
        transactionSummary.setTotalTransactionAmount(calculateTotalTransactionAmount(line));
        return transactionSummary;
    }

    private int calculateTotalTransactionAmount(String line) {
        String[] quantityLong = properties.getProperty("quantityLong").split("-");
        int longAmount = Integer.parseInt(line.substring(Integer.parseInt(quantityLong[0]), Integer.parseInt(quantityLong[1])));
        String[] quantityShort = properties.getProperty("quantityShort").split("-");
        int shortAmount = Integer.parseInt(line.substring(Integer.parseInt(quantityShort[0]), Integer.parseInt(quantityShort[1])));
        return longAmount - shortAmount;
    }
}
