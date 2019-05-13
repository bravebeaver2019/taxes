package com.test.taxes.tax;

import com.test.taxes.model.OrderLine;
import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import com.test.taxes.model.TaxedProduct;

import java.math.BigDecimal;
import java.util.function.Function;

public class TaxModelProvider {

    private static final BigDecimal TEN_PERCENT  = new BigDecimal("0.10");
    private static final BigDecimal FIVE_PERCENT = new BigDecimal("0.05");

    /**
     * This function applies taxes to an UNTAXED orderline
     * and converts into a TAXED orderline.
     * @return taxed
     */
    public Function<OrderLine<Product>, OrderLine<TaxedProduct>> getTaxModel() {
        // so far only one tax model is implemented
        // here we would have different tax types for different markets, etc
        return o -> {
            Product product = o.getProduct();
            BigDecimal basePrice = product.getBasePrice();
            double tax = 0;
            if(product.getCategory().equals(ProductCategory.OTHER)) { //10% Base
                tax+=(basePrice.multiply(TEN_PERCENT)).floatValue();
            }
            if(product.isImported()) { // 5% import
                tax+=(basePrice.multiply(FIVE_PERCENT)).floatValue();
            }
            return new OrderLine(o.getAmount(),
                    new TaxedProduct(product, basePrice.add(new BigDecimal(tax).setScale(2, BigDecimal.ROUND_CEILING))));
        };
    }
}
