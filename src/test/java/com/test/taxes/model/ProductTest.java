package com.test.taxes.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void testRound2Decimals() {
        assertEquals(new Product(null, ProductCategory.BOOK, false, new BigDecimal("1.99")).basePrice,
                new BigDecimal("1.99"));
        assertEquals(new Product(null, ProductCategory.BOOK, false, new BigDecimal("1.991")).basePrice,
                new BigDecimal("2.00"));
        assertEquals(new Product(null, ProductCategory.BOOK, false, new BigDecimal("1.9")).basePrice,
                new BigDecimal("1.90"));
        assertEquals(new Product(null, ProductCategory.BOOK, false, new BigDecimal("1.00001")).basePrice,
                new BigDecimal("1.01"));
    }
}
