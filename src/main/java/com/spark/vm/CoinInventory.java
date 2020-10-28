package com.spark.vm;

import com.spark.vm.model.Coin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * 10, 10, 5, 1, 1
 *
 * 30 - 25 = 5, ()
 * 50 - 25 = 25
 *
 * 15
 */
public class CoinInventory {

    private final Map<Coin, Integer> coinQuantityMap = new TreeMap<>(new Comparator<Coin>() {
        @Override
        public int compare(Coin o1, Coin o2) {
            return o2.getValue() - o1.getValue();
        }
    });

    public CoinInventory() {
        for (Coin coin : Coin.values()) {
            coinQuantityMap.put(coin, 0);
        }
    }

    public int getQuantityForCoin(Coin coin) {
        return coinQuantityMap.getOrDefault(coin, 0);
    }

    public void addQuantityForCoin(Coin coin, int quantity) {
        Integer q = coinQuantityMap.getOrDefault(coin, 0);
        coinQuantityMap.put(coin, q + quantity);
    }

    public Map<Coin, Integer> getRemainingChange(List<Coin> inputCoins, int price) {
        int remainingValue = inputCoins.stream().map(Coin::getValue).reduce(Integer::sum).get() - price;
        Map<Coin, Integer> changeToReturn = new HashMap<>();
        while (remainingValue > 0) {
            Coin coin = Coin.fromValue(remainingValue);
            int change = coin != null ? getQuantityForCoin(coin) : 0;
            if (change == 0) {
                // we do not have exact change
                Iterator<Map.Entry<Coin, Integer>> iterator = coinQuantityMap.entrySet().iterator();
                Coin foundChange = null;
                while (iterator.hasNext()) {
                    Map.Entry<Coin, Integer> next = iterator.next();
                    if (next.getKey().getValue() < remainingValue && next.getValue() > 0) {
                        foundChange = next.getKey();
                        break;
                    }
                }
                if (foundChange != null) {
                    remainingValue -= foundChange.getValue();
                    coinQuantityMap.put(foundChange, coinQuantityMap.get(foundChange) - 1);
                    changeToReturn.put(foundChange, changeToReturn.getOrDefault(foundChange, 0) + 1);
                } else {
                    // no change to return!
                    throw new IllegalArgumentException("No sufficient change to return");
                }
            } else {
                if (change > 0) {
                    remainingValue = 0;
                    coinQuantityMap.put(coin, --change);
                    changeToReturn.put(coin, changeToReturn.getOrDefault(coin, 0) + 1);
                }
            }
        }
        return changeToReturn;
    }
}
