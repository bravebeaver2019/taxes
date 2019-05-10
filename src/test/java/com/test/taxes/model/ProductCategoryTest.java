package com.test.taxes.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductCategoryTest {
    @Test
    public void testValueOf() {
        assertEquals(ProductCategory.valueOf("BOOK"), ProductCategory.BOOK);
        assertEquals(ProductCategory.valueOf("OTHER"), ProductCategory.OTHER);
        assertEquals(ProductCategory.valueOf("FOOD"), ProductCategory.FOOD);
        assertEquals(ProductCategory.valueOf("MEDICAL"), ProductCategory.MEDICAL);
    }
}
