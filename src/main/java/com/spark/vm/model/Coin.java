package com.spark.vm.model;

public enum Coin {

    ONE(1),
    FIVE(5),
    TEN(10),
    TWENTY_FIVE(25);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public static Coin fromValue(Integer value) {
        for (Coin coin : values()) {
            if (coin.getValue() == value) {
                return coin;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}