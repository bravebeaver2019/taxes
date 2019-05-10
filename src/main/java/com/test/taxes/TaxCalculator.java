package com.test.taxes;

import com.test.taxes.model.Product;
import com.test.taxes.model.TaxedProduct;
import com.test.taxes.tax.TaxModelProvider;

import java.util.List;
import java.util.stream.Collectors;

public class TaxCalculator {

    TaxModelProvider taxModelProvider;

    public TaxCalculator(TaxModelProvider taxModelProvider) {
        this.taxModelProvider = taxModelProvider;
    }

    public List<TaxedProduct> calculate(List<Product> original) {
        return original.stream().map(taxModelProvider.getTaxModel()).collect(Collectors.toList());
    }
}
