package com.spark.vm.model;

import java.util.List;

public class Transaction {

    private Product selectedProduct;
    private List<Coin> insertedCoins;
    private TransactionState transactionState;

    public Transaction(Product selectedProduct, List<Coin> initialCoins) {
        this.selectedProduct = selectedProduct;
        this.insertedCoins = initialCoins;
        transactionState = TransactionState.STARTED;
    }

    public TransactionState getState() {
        return transactionState;
    }

    public void setState(TransactionState newTransactionState) {
        this.transactionState = newTransactionState;
    }
}
