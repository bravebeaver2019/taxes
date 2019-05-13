package com.test.taxes.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Purchase {

    private List<OrderLine<TaxedProduct>> products;

    public Purchase(List<OrderLine<TaxedProduct>> products) {
        this.products = products;
    }

    public List<OrderLine<TaxedProduct>> getProducts() {
        return products;
    }

    public BigDecimal getTotalAmount() {
        return products.stream().map( o -> o.getProduct().getTaxedPrice() ).collect(Collectors.toList()).stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalTaxes() {
        return getTotalAmount().subtract(products.stream().map( o -> o.getProduct().getBasePrice() ).collect(Collectors.toList()).stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "products=" + products +
                '}' +
                "\ntotal: " + getTotalAmount() +
                "\ntotal taxes: " + getTotalTaxes() + "";
    }
}
