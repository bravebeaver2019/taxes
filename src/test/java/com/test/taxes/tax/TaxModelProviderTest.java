package com.test.taxes.tax;

import com.test.taxes.model.Product;
import com.test.taxes.model.ProductCategory;
import com.test.taxes.model.TaxedProduct;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class TaxModelProviderTest {

    @Test
    public void testModelBook() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Book", ProductCategory.BOOK, false, new BigDecimal("12.49"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("12.49")));
    }

    @Test
    public void testMusicCD() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Music CD", ProductCategory.OTHER, false, new BigDecimal("14.99"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("16.49")));
    }

    @Test
    public void testChocolateBar() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Chocolate bar", ProductCategory.FOOD, false, new BigDecimal("0.85"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("0.85")));
    }

    @Test
    public void testImportedChocolateBox() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Imported Chocolate Box", ProductCategory.FOOD, true, new BigDecimal("10.00"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("10.50")));
    }

    @Test
    public void testImportedPerfumeBottle() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Imported Bottle of Perfume", ProductCategory.OTHER, true, new BigDecimal("47.50"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("54.65")));
    }

    @Test
    public void testImportedPerfumeBottle2() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Imported Bottle of Perfume", ProductCategory.OTHER, true, new BigDecimal("27.99"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("32.19")));
    }

    @Test
    public void testPerfumeBottle() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Bottle of Perfume", ProductCategory.OTHER, false, new BigDecimal("18.99"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("20.89")));
    }

    @Test
    public void testPills() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Pills", ProductCategory.MEDICAL, false, new BigDecimal("9.75"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("9.75")));
    }

    @Test
    public void testMoreImportedChocolates() {
        TaxModelProvider tmp = new TaxModelProvider();
        Function<Product, TaxedProduct> taxModel = tmp.getTaxModel();
        Product importedBook = new Product("Imported Chocolates", ProductCategory.FOOD, true, new BigDecimal("11.25"));
        assertEquals(taxModel.apply(importedBook), new TaxedProduct(importedBook, new BigDecimal("11.85")));
    }
}
