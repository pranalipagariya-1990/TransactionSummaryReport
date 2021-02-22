package com.file.parser;

import com.file.model.TransactionSummary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

class FileOperationsServiceImplTest {

    private FileOperationsService fileparser =new FileOperationsServiceImpl();
    @Test
    void shouldReadAndProcessFileCountTest() throws IOException {

        String fileName = "src/test/resources/Input-1.txt";
        Set<TransactionSummary> transactionSummary = fileparser.readAndProcessFile(fileName);

        Assertions.assertEquals(transactionSummary.size(),2);
    }
    @Test
    void shouldThrowErrorIfFileDoesNotExists() throws IOException {
        String wrongFileName = "src/test/resources/wrongFileName.txt";
        Assertions.assertThrows(IOException.class,()->fileparser.readAndProcessFile(wrongFileName));
    }

    @Test
    void shouldThrowErrorIfFileContainsIncorrectData() throws IOException {
        String incorrectDataFile = "src/test/resources/IncorrectFileData.txt";
        Assertions.assertThrows(IOException.class,()->fileparser.readAndProcessFile(incorrectDataFile));
    }

    @Test
    void shouldThrowErrorWhenQuantityIsNotInteger() throws NumberFormatException {
        String fileName = "src/test/resources/QuantityNotInteger.txt";
        Assertions.assertThrows(NumberFormatException.class,()->fileparser.readAndProcessFile(fileName));
    }
}
