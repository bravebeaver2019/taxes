package com.test.taxes.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Product {

    protected String name;
    protected ProductCategory category;
    protected boolean imported;
    protected BigDecimal basePrice;

    public Product() {
    }

    public Product(String name, ProductCategory category, boolean imported, BigDecimal basePrice) {
        this.name = name;
        this.category = category;
        this.imported = imported;
        this.basePrice = basePrice.setScale(2, RoundingMode.CEILING);
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public boolean isImported() {
        return imported;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return isImported() == product.isImported() &&
                Objects.equals(getName(), product.getName()) &&
                getCategory() == product.getCategory() &&
                Objects.equals(getBasePrice(), product.getBasePrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCategory(), isImported(), getBasePrice());
    }
}
