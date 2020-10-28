package com.spark.vm;

import com.spark.vm.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductInventoryTest {

    @InjectMocks
    private ProductInventory productInventory;

    @Test
    void testGetProductQuantity() {
        int quantity = productInventory.getQuantityForProduct(Product.SPARK_PASTA);
        assertEquals(0, quantity);
    }

    @Test
    void testAddProduct() {
        productInventory.addProductQuantity(Product.SPARK_PASTA, 1);
        assertEquals(1, productInventory.getQuantityForProduct(Product.SPARK_PASTA));
    }

}