package com.test.taxes.serializer;

import com.test.taxes.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProductCSVDeserializer {

    ProductCSVLineDeserializer lineDeserializer;

    public ProductCSVDeserializer(ProductCSVLineDeserializer lineDeserializer) {
        this.lineDeserializer = lineDeserializer;
    }

    public List<Product> deserialize(InputStream input) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                if(!line.trim().isEmpty()) {
                    products.add(lineDeserializer.readProduct(line));
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Malformed csv input stream ", ioe); //todo: change to checked exception
        }
        return products;
    }
}
