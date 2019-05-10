package com.test.taxes;

import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import com.test.taxes.model.TaxedProduct;
import com.test.taxes.tax.TaxModelProvider;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

    public static final BigDecimal TAXED_PRICE = new BigDecimal("1.00");

    @Test
    public void testCalculator() {

        TaxModelProvider mockTaxModelProvider = new TaxModelProvider() {
            @Override
            public Function<Product, TaxedProduct> getTaxModel() {
                return o -> new TaxedProduct(o, TAXED_PRICE);
            }
        };

        TaxCalculator calculator = new TaxCalculator(mockTaxModelProvider);
        calculator.calculate(Arrays.asList(
                new Product(null, ProductCategory.BOOK, false, BigDecimal.ONE),
                new Product(null, ProductCategory.MEDICAL, true, BigDecimal.ONE)
        )).stream().forEach( taxedProduct -> assertEquals(TAXED_PRICE, taxedProduct.getTaxedPrice()));
    }
}
