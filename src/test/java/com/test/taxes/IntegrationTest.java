package com.test.taxes;

import com.test.taxes.model.OrderLine;
import com.test.taxes.model.Product;
import com.test.taxes.model.TaxedProduct;
import com.test.taxes.serializer.ProductCSVDeserializer;
import com.test.taxes.serializer.ProductCSVLineDeserializer;
import com.test.taxes.tax.TaxModelProvider;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IntegrationTest {

    @Test
    public void input1Test() {
        InputStream in = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input1.csv");

        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        ProductCSVDeserializer deserializer = new ProductCSVDeserializer(lineDeserializer);
        List<OrderLine<Product>> originalProducts = deserializer.deserialize(in);

        TaxModelProvider taxModelProvider = new TaxModelProvider();
        TaxCalculator calculator = new TaxCalculator(taxModelProvider);
        List<OrderLine<TaxedProduct>> taxedProducts = calculator.calculate(originalProducts);
        taxedProducts.stream().forEach( tp -> System.err.println(tp));

    }
}