package com.test.taxes.serializer;

import com.test.taxes.model.OrderLine;
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
    public static OrderLine<Product> ORDER = new OrderLine(1, PRODUCT);

    @Test
    public void testCSVDeserializer() {
        // todo: I decided not to use a mocking library due to simplicity fo this case
        ProductCSVLineDeserializer mock = new ProductCSVLineDeserializer(null) {
            @Override
            public OrderLine<Product> readProduct(String csvLine) {
                return ORDER;
            }
        };

        ProductCSVDeserializer deserializer = new ProductCSVDeserializer(mock);
        List<OrderLine<Product>> products = deserializer.deserialize(
                new ByteArrayInputStream((
                        "foo\n" +
                        "bar\n" +
                        "tar\n" +
                        "jar\n").getBytes()));
        assertEquals(products.size(), 4);
        products.stream().forEach( p -> assertEquals(ORDER, p));
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
