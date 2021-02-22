package com.file.model;

import lombok.Data;

import java.util.Objects;

@Data
public class TransactionSummary {
    private String clientInformation;
    private String productInformation;
    private Integer totalTransactionAmount;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionSummary that = (TransactionSummary) o;
        return clientInformation.equals(that.clientInformation) &&
                productInformation.equals(that.productInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientInformation, productInformation);
    }
}
