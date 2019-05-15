package com.test.taxes.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PurchaseTest {

    @Test
    public void testPurchase() {

        Product product1 = new Product(null, ProductCategory.BOOK, false, new BigDecimal("5"));
        Product product2 = new Product(null, ProductCategory.BOOK, false, new BigDecimal("7"));
        TaxedProduct taxedProduct1 = new TaxedProduct(product1, new BigDecimal("11"));
        TaxedProduct taxedProduct2 = new TaxedProduct(product2, new BigDecimal("12"));
        OrderLine<TaxedProduct> orderLine1 = new OrderLine<>(new Integer(1), taxedProduct1);
        OrderLine<TaxedProduct> orderLine2 = new OrderLine<>(new Integer(1), taxedProduct2);

        Purchase purchase = new Purchase(Arrays.asList(orderLine1, orderLine2));
        assertEquals(new BigDecimal("23"), purchase.getTotalAmount());
        assertEquals(new BigDecimal("11.00"), purchase.getTotalTaxes());
    }
}
