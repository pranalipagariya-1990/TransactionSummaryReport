package com.file.parser;

import com.file.model.TransactionSummary;

import java.io.IOException;
import java.util.Set;

public interface FileOperationsService {

    Set<TransactionSummary> readAndProcessFile(String fileName) throws IOException;

    boolean generateTransactionSummaryReportFile(Set<TransactionSummary> transactionSummaryList) throws IOException;
}
