package com.test.taxes;

import com.test.taxes.model.Product;
import com.test.taxes.model.TaxedProduct;
import com.test.taxes.serializer.ProductCSVDeserializer;
import com.test.taxes.serializer.ProductCSVLineDeserializer;
import com.test.taxes.tax.TaxModelProvider;

import java.io.InputStream;
import java.util.List;

public class TaxesMain {

    public static void main(String[] args) {

        InputStream in = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input.csv");

        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        ProductCSVDeserializer deserializer = new ProductCSVDeserializer(lineDeserializer);
        List<Product> originalProducts = deserializer.deserialize(in);

        TaxModelProvider taxModelProvider = new TaxModelProvider();
        TaxCalculator calculator = new TaxCalculator(taxModelProvider);
        List<TaxedProduct> taxedProducts = calculator.calculate(originalProducts);
        System.err.println(taxedProducts.toString());
    }
}
