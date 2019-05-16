package com.test.taxes.tax;

import com.test.taxes.model.OrderLine;
import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import com.test.taxes.model.TaxedProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TaxModelProvider {

    private static final BigDecimal TEN_PERCENT  = new BigDecimal("0.10");
    private static final BigDecimal FIVE_PERCENT = new BigDecimal("0.05");

    /**
     * This function applies taxes to an UNTAXED orderline
     * and converts into a TAXED orderline.
     * @return taxed
     */
    public Function<OrderLine<Product>, OrderLine<TaxedProduct>> getTaxModel(
            BiFunction<BigDecimal, BigDecimal, BigDecimal> taxFunction) {
        // so far only one tax model is implemented
        // here we would have different tax types for different markets, etc
        return o -> {
            Product product = o.getProduct();
            BigDecimal basePrice = product.getBasePrice();
            BigDecimal tax = BigDecimal.ZERO;
            if(product.getCategory().equals(ProductCategory.OTHER)) { //10% Base
                tax = calculateTax().apply(basePrice, TEN_PERCENT);
            }
            if(product.isImported()) { // 5% import
                tax = tax.add(calculateTax().apply(basePrice, FIVE_PERCENT));
            }
            return new OrderLine(o.getAmount(),
                    new TaxedProduct(product, basePrice.add(tax)));
        };
    }

    /**
     * This function calculates the tax applied to a price for a given rate.
     * @return the tax to be applied to the product
     */
    public BiFunction<BigDecimal, BigDecimal, BigDecimal> calculateTax() {
        // same as before here, this function rounds the tax calculated to the closest 0.05
        // other models could be used by implementing a factory here
        return (basePrice, rate) -> {
            BigDecimal amount = rate.multiply(basePrice);
            BigDecimal result = new BigDecimal(Math.ceil(amount.doubleValue() * 20) / 20);
            return result.setScale(2, RoundingMode.CEILING);
        };
    }
}
