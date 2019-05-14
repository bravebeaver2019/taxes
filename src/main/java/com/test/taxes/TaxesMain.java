package com.test.taxes;

import com.test.taxes.model.OrderLine;
import com.test.taxes.model.Product;
import com.test.taxes.model.Purchase;
import com.test.taxes.model.TaxedProduct;
import com.test.taxes.serializer.ProductCSVDeserializer;
import com.test.taxes.serializer.ProductCSVLineDeserializer;
import com.test.taxes.tax.TaxModelProvider;

import java.io.InputStream;
import java.util.List;

public class TaxesMain {

    public static void main(String[] args) {

        ProductCSVLineDeserializer lineDeserializer = new ProductCSVLineDeserializer(",");
        ProductCSVDeserializer deserializer = new ProductCSVDeserializer(lineDeserializer);
        TaxModelProvider taxModelProvider = new TaxModelProvider();
        TaxCalculator calculator = new TaxCalculator(taxModelProvider);

        System.err.println("input1 --------------------------");
        InputStream input1 = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input1.csv");
        List<OrderLine<Product>> originalProducts1 = deserializer.deserialize(input1);
        List<OrderLine<TaxedProduct>> taxedProducts1 = calculator.calculate(originalProducts1);
        System.err.println(new Purchase(taxedProducts1).toString());
        System.err.println("input1 --------------------------");
        System.err.println();

        System.err.println("input2 --------------------------");
        InputStream input2 = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input2.csv");
        List<OrderLine<Product>> originalProducts2 = deserializer.deserialize(input2);
        List<OrderLine<TaxedProduct>> taxedProducts2 = calculator.calculate(originalProducts2);
        System.err.println(new Purchase(taxedProducts2).toString());
        System.err.println("input2 --------------------------");
        System.err.println();

        System.err.println("input3 --------------------------");
        InputStream input3 = TaxesMain.class.getClassLoader()
                .getResourceAsStream("input3.csv");
        List<OrderLine<Product>> originalProducts3 = deserializer.deserialize(input3);
        List<OrderLine<TaxedProduct>> taxedProducts3 = calculator.calculate(originalProducts3);
        System.err.println(new Purchase(taxedProducts3).toString());
        System.err.println("input3 --------------------------");
        System.err.println();
    }
}
