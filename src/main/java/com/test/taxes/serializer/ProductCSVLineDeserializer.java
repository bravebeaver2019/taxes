package com.test.taxes.serializer;

import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class ProductCSVLineDeserializer {

    private String delimiter;

    public ProductCSVLineDeserializer(String delimiter) {
        this.delimiter = delimiter;
    }

    public Product readProduct(String csvLine) {
        StringTokenizer tokenizer = new StringTokenizer(csvLine,delimiter);
        String name = tokenizer.nextToken().trim();
        boolean imported = tokenizer.nextToken().trim().equals("true");
        ProductCategory category;
        try {
            category = ProductCategory.valueOf(tokenizer.nextToken().trim().toUpperCase());
        } catch(IllegalArgumentException iae) {
            category = ProductCategory.OTHER;
        }
        BigDecimal basePrice = new BigDecimal(tokenizer.nextToken().trim());
        return new Product(name, category, imported, basePrice);
    }
}
