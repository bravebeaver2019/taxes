package com.test.taxes;

import com.test.taxes.model.*;
import com.test.taxes.serializer.ProductCSVDeserializer;
import com.test.taxes.serializer.ProductCSVLineDeserializer;
import com.test.taxes.tax.TaxModelProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    TaxCalculator calculator;
    ProductCSVDeserializer deserializer;

    @Before
    public void init() {
        TaxModelProvider taxModelProvider = new TaxModelProvider();
        calculator = new TaxCalculator(taxModelProvider);
        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        deserializer = new ProductCSVDeserializer(lineDeserializer);
    }

    @Test
    public void input1Test() {
        InputStream in = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input1.csv");
        List<OrderLine<TaxedProduct>> taxedProducts = calculator.calculate(deserializer.deserialize(in));
        assertEquals(new BigDecimal("29.83"), new Purchase(taxedProducts).getTotalAmount());
    }

    @Test
    public void input2Test() {
        InputStream in = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input2.csv");
        List<OrderLine<TaxedProduct>> taxedProducts = calculator.calculate(deserializer.deserialize(in));
        assertEquals(new BigDecimal("65.15"), new Purchase(taxedProducts).getTotalAmount());
    }

    @Test
    public void input3Test() {
        InputStream in = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input3.csv");
        List<OrderLine<TaxedProduct>> taxedProducts = calculator.calculate(deserializer.deserialize(in));
        assertEquals(new BigDecimal("74.68"), new Purchase(taxedProducts).getTotalAmount());
    }
}
