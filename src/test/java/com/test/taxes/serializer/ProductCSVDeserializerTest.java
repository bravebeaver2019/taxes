package com.test.taxes.serializer;

import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCSVDeserializerTest {

    public static final Product PRODUCT = new Product("name", ProductCategory.OTHER, false, BigDecimal.ZERO);

    @Test
    public void testCSVDeserializer() {
        ProductCSVLineDeserializer mock = new ProductCSVLineDeserializer(null) {
            @Override
            public Product readProduct(String csvLine) {
                return PRODUCT;
            }
        };

        ProductCSVDeserializer deserializer = new ProductCSVDeserializer(mock);
        List<Product> products = deserializer.deserialize(
                new ByteArrayInputStream((
                        "foo\n" +
                        "bar\n" +
                        "tar\n" +
                        "jar\n").getBytes()));
        assertEquals(products.size(), 4);
        products.stream().forEach( p -> assertEquals(PRODUCT, p));
    }

    @Test(expected = RuntimeException.class)
    public void testIOException() {
        ProductCSVDeserializer deserializer = new ProductCSVDeserializer(new ProductCSVLineDeserializer(","));
        InputStream unreadable_stream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("unreadable stream");
            }
        };
        deserializer.deserialize(unreadable_stream);
    }
}