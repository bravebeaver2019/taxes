package com.test.taxes.model;

import java.util.Objects;

public class OrderLine<T extends Product> {

    private Integer amount;
    private T product;

    public OrderLine(Integer amount, T product) {
        this.amount = amount;
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public T getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "" +
                "" + amount +
                " " + product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine)) return false;
        OrderLine<?> orderLine = (OrderLine<?>) o;
        return Objects.equals(getAmount(), orderLine.getAmount()) &&
                Objects.equals(getProduct(), orderLine.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getProduct());
    }
}
