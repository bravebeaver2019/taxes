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
        BigDecimal v0 = taxedPrice.multiply(
                new BigDecimal("20")).setScale(0, RoundingMode.UP).setScale(2);
        this.taxedPrice = v0.divide(new BigDecimal("20"), RoundingMode.CEILING);
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
