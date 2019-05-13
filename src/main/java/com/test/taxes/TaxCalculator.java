package com.test.taxes;

import com.test.taxes.model.OrderLine;
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

    /**
     * This method receives a list of order lines of UNTAXED products
     * and returns a list of order lines of TAXED products
     * @param original untaxed
     * @return taxed
     */
    public List<OrderLine<TaxedProduct>> calculate(List<OrderLine<Product>> original) {
        return original.stream().map(taxModelProvider.getTaxModel()).collect(Collectors.toList());
    }
}
