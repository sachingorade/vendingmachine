package com.spark.vm;

import com.spark.vm.model.Coin;
import com.spark.vm.model.Product;
import com.spark.vm.model.Transaction;
import com.spark.vm.model.TransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class VendingMachine {

    private static final Logger logger = LoggerFactory.getLogger(VendingMachine.class);

    private CoinInventory coinInventory = new CoinInventory();

    public Transaction acceptOrderForProduct(Product product, List<Coin> totalCoins) {
        Optional<Integer> sum = totalCoins.stream().map(Coin::getValue).reduce(Integer::sum);
        if (sum.isEmpty()) {
            throw new IllegalArgumentException("Coins are required for the order");
        }
        Transaction transaction = new Transaction(product, totalCoins);
        if (sum.get() < product.getPrice()) {
            // TODO continue the transaction with proper message to the user
            logger.info("Insufficient coins, please insert [{}] coins", (product.getPrice() - sum.get()));
            transaction.setState(TransactionState.WAITING_FOR_REMAINING_COINS);
            return transaction;
        }
        return null;
    }

}
