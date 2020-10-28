package com.spark.vm;

import com.spark.vm.model.Coin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CoinInventoryTest {

    @InjectMocks
    private CoinInventory coinInventory;

    @Test
    void testGetCoinQuantity() {
        int quantity = coinInventory.getQuantityForCoin(Coin.ONE);
        assertEquals(0, quantity);
    }

    @Test
    void testAddCoinQuantity() {
        coinInventory.addQuantityForCoin(Coin.ONE, 1);
        assertEquals(1, coinInventory.getQuantityForCoin(Coin.ONE));
    }

    @Test
    void testSufficientChange() {
        coinInventory.addQuantityForCoin(Coin.TWENTY_FIVE, 1);
        coinInventory.addQuantityForCoin(Coin.TEN, 2);
        coinInventory.addQuantityForCoin(Coin.FIVE, 1);
        coinInventory.addQuantityForCoin(Coin.ONE, 2);

        Map<Coin, Integer> change = coinInventory.getRemainingChange(Arrays.asList(Coin.TEN, Coin.TEN, Coin.TEN), 25);
        assertEquals(1, change.size());
        assertEquals(1, change.get(Coin.FIVE));
    }

    @Test
    void testSufficientChange35() {
        // 25, 10, 10, 5, 1, 1
        // Input coins: 2 * 25 = 50
        // Price = 35
        // Change to return 10, 5
        coinInventory.addQuantityForCoin(Coin.TWENTY_FIVE, 1);
        coinInventory.addQuantityForCoin(Coin.TEN, 2);
        coinInventory.addQuantityForCoin(Coin.FIVE, 1);
        coinInventory.addQuantityForCoin(Coin.ONE, 2);

        Map<Coin, Integer> change = coinInventory.getRemainingChange(Arrays.asList(Coin.TWENTY_FIVE, Coin.TWENTY_FIVE), 35);
        assertEquals(2, change.size());
        assertEquals(1, change.get(Coin.TEN));
        assertEquals(1, change.get(Coin.FIVE));
    }

    @Test
    void testSufficientChangeNoSufficientMultipleChange() {
        // 10, 10, 5, 1, 1
        // Input coins = 2 * 25 = 50
        // Price = 25
        // Change to return 10, 10, 5
        coinInventory.addQuantityForCoin(Coin.TEN, 2);
        coinInventory.addQuantityForCoin(Coin.FIVE, 1);
        coinInventory.addQuantityForCoin(Coin.ONE, 2);

        Map<Coin, Integer> change = coinInventory.getRemainingChange(Arrays.asList(Coin.TWENTY_FIVE, Coin.TWENTY_FIVE), 25);
        assertEquals(2, change.size());
        assertEquals(2, change.get(Coin.TEN));
        assertEquals(1, change.get(Coin.FIVE));
    }

}