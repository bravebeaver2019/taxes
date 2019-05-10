package com.test.taxes.tax;

import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import com.test.taxes.model.TaxedProduct;

import java.math.BigDecimal;
import java.util.function.Function;

public class TaxModelProvider {

    public Function<Product, TaxedProduct> getTaxModel() {
        // so far only one tax model is implemented
        // here we would have different tax types for different markets, etc
        return o -> {
            BigDecimal basePrice = o.getBasePrice();
            double tax = 0;
            if(o.getCategory().equals(ProductCategory.OTHER)) { //10% Base
                tax+=(basePrice.multiply(new BigDecimal("0.10"))).floatValue();
            }
            if(o.isImported()) { // 5% import
                tax+=(basePrice.multiply(new BigDecimal("0.05"))).floatValue();
            }
            return new TaxedProduct(o, basePrice.add(new BigDecimal(tax).setScale(2, BigDecimal.ROUND_CEILING)));
        };
    }
}
