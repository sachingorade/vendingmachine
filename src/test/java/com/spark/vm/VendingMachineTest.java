package com.spark.vm;

import com.spark.vm.model.Coin;
import com.spark.vm.model.Product;
import com.spark.vm.model.Transaction;
import com.spark.vm.model.TransactionState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VendingMachineTest {

    @InjectMocks
    private VendingMachine vendingMachine;

    @Test
    void testAcceptOrderValidateEnoughCurrency() {
        Transaction transaction = vendingMachine.acceptOrderForProduct(Product.SPARK_PASTA, Collections.singletonList(Coin.TEN));
        assertEquals(TransactionState.WAITING_FOR_REMAINING_COINS, transaction.getState());
    }

    @Test
    void testAcceptOrderValidateEnoughChange() {
        Transaction transaction = vendingMachine.acceptOrderForProduct(Product.SPARK_PASTA, Arrays.asList(Coin.TEN, Coin.TEN, Coin.TEN));
        assertEquals(TransactionState.CANCELLED, transaction.getState());
    }

}