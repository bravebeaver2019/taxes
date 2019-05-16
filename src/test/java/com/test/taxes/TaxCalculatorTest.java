package com.test.taxes;

import com.test.taxes.model.OrderLine;
import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import com.test.taxes.model.TaxedProduct;
import com.test.taxes.tax.TaxModelProvider;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

    public static final BigDecimal TAXED_PRICE = new BigDecimal("1.00");

    @Test
    public void testCalculator() {

        TaxModelProvider mockTaxModelProvider = new TaxModelProvider() {
            @Override
            public Function<OrderLine<Product>, OrderLine<TaxedProduct>> getTaxModel(
                    BiFunction<BigDecimal, BigDecimal, BigDecimal> taxFunction) {
                return o -> new OrderLine<>(o.getAmount(), new TaxedProduct(o.getProduct(), TAXED_PRICE));
            }

            @Override
            public BiFunction<BigDecimal, BigDecimal, BigDecimal> calculateTax() {
                return super.calculateTax();
            }
        };

        TaxCalculator calculator = new TaxCalculator(mockTaxModelProvider);
        calculator.calculate(Arrays.asList(
                new OrderLine<>(1, new Product(null, ProductCategory.BOOK, false, BigDecimal.ONE)),
                new OrderLine<>(1, new Product(null, ProductCategory.MEDICAL, true, BigDecimal.ONE))
        )).stream().forEach( taxedProduct -> assertEquals(TAXED_PRICE, taxedProduct.getProduct().getTaxedPrice()));
    }
}
