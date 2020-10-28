package com.spark.vm;

import com.spark.vm.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductInventory {

    private Map<Product, Integer> productQuantityMap = new HashMap<>();


    public int getQuantityForProduct(Product product) {
        return productQuantityMap.getOrDefault(product, 0);
    }

    public void addProductQuantity(Product product, int quantity) {
        Integer q = productQuantityMap.getOrDefault(product, 0);
        productQuantityMap.put(product, q + quantity);
    }
}
