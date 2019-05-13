package com.test.taxes.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class TaxedProduct extends Product {

    protected BigDecimal taxedPrice;

    public TaxedProduct(Product product, BigDecimal taxedPrice) {
        this.name = product.name;
        this.category = product.category;
        this.imported = product.imported;
        this.basePrice = product.basePrice;
        this.taxedPrice = new BigDecimal(0.05*(Math.ceil(Math.abs(taxedPrice.doubleValue()/0.05))))
                .setScale(2, BigDecimal.ROUND_CEILING);
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxedProduct)) return false;
        if (!super.equals(o)) return false;
        TaxedProduct that = (TaxedProduct) o;
        return Objects.equals(getTaxedPrice(), that.getTaxedPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTaxedPrice());
    }

    @Override
    public String toString() {
        return ((imported)?"imported ":"") +
                "" + name +
                " at " + taxedPrice + "\n";
    }
}
