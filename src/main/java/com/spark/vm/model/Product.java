package com.spark.vm.model;

import java.util.Objects;

public enum Product {

    SPARKLING_WATER("SparklingWater", 25),
    SPARK_PASTA("SparkPasta", 35),
    SPARK_SODA("Sparksoda", 45),
    ;

    private final String name;
    private final int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
