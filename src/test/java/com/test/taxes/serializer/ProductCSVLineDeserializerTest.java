package com.test.taxes.serializer;

import com.test.taxes.model.OrderLine;
import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductCSVLineDeserializerTest {

    @Test
    public void testBookProduct() {
        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        OrderLine<Product> product = lineDeserializer.readProduct("1, product 2, false , Book, 3.99");
        assertEquals(new OrderLine(1, new Product("product 2", ProductCategory.BOOK, false, new BigDecimal("3.99"))), product);
    }

    @Test
    public void testFoodProduct() {
        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        OrderLine<Product>  product = lineDeserializer.readProduct("1, product 3    , true    , Food, 3.991");
        assertEquals(new OrderLine(1, new Product("product 3", ProductCategory.FOOD, true, new BigDecimal("3.991"))), product);
    }

    @Test
    public void testMedicalProduct() {
        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        OrderLine<Product>  product = lineDeserializer.readProduct("1, product 3    , true    , mEdiCal, 1");
        assertEquals(new OrderLine(1, new Product("product 3", ProductCategory.MEDICAL, true, new BigDecimal("1"))), product);
    }

    @Test
    public void testUnknownCategory() {
        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        OrderLine<Product>  product = lineDeserializer.readProduct("1, product 3    , true    , XXXXXX, 3.991");
        assertEquals(new OrderLine(1, new Product("product 3", ProductCategory.OTHER, true, new BigDecimal("3.991"))), product);
    }

    @Test
    public void testRoundPrice() {
        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        OrderLine<Product>  product = lineDeserializer.readProduct("1, product 3    , true    , XXXXXX, 3.991");
        assertEquals(new OrderLine(1, new Product("product 3", ProductCategory.OTHER, true, new BigDecimal("4.00"))), product);
    }
}
