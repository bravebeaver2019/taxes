package com.test.taxes.tax;

import com.test.taxes.model.OrderLine;
import com.test.taxes.model.Product;
import com.test.taxes.model.TaxedProduct;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.test.taxes.model.ProductCategory.*;
import static org.junit.Assert.assertEquals;

public class TaxModelProviderTest {

    TaxModelProvider tmp = new TaxModelProvider();
    Function<OrderLine<Product>, OrderLine<TaxedProduct>> taxModel = tmp.getTaxModel();

    @Test
    public void testModelBook() {
        Product importedBook = new Product("Book", BOOK, false, new BigDecimal("12.49"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("12.49"))));
    }

    @Test
    public void testMusicCD() {
        Product importedBook = new Product("Music CD", OTHER, false, new BigDecimal("14.99"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("16.49"))));
    }

    @Test
    public void testChocolateBar() {
        Product importedBook = new Product("Chocolate bar", FOOD, false, new BigDecimal("0.85"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("0.85"))));
    }

    @Test
    public void testImportedChocolateBox() {
        Product importedBook = new Product("Imported Chocolate Box", FOOD, true, new BigDecimal("10.00"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("10.50"))));
    }

    @Test
    public void testImportedPerfumeBottle() {
        Product importedBook = new Product("Imported Bottle of Perfume", OTHER, true, new BigDecimal("47.50"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("54.65"))));
    }

    @Test
    public void testImportedPerfumeBottle2() {
        Product importedBook = new Product("Imported Bottle of Perfume", OTHER, true, new BigDecimal("27.99"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("32.19"))));
    }

    @Test
    public void testPerfumeBottle() {
        Product importedBook = new Product("Bottle of Perfume", OTHER, false, new BigDecimal("18.99"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("20.89"))));
    }

    @Test
    public void testPills() {
        Product importedBook = new Product("Pills", MEDICAL, false, new BigDecimal("9.75"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("9.75"))));
    }

    @Test
    public void testMoreImportedChocolates() {
        Product importedBook = new Product("Imported Chocolates", FOOD, true, new BigDecimal("11.25"));
        assertEquals(taxModel.apply(new OrderLine<>(1, importedBook)),
                new OrderLine<>(1, new TaxedProduct(importedBook, new BigDecimal("11.85"))));
    }
}
