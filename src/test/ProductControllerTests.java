package test;

import controllers.ProductController;
import exceptions.DataAccessException;
import models.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductControllerTests {
    private ProductController productController;

    @Before
    public void setUp() {
        try {
            this.productController = new ProductController();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindProductByBarcode() {
        Product p = null;
        try {
            p = this.productController.findByBarcode(93766442);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        assertEquals("Dried Apple", p.getName());
        assertEquals(17, p.getId());
    }

    @Test
    public void testFindProductByInvalidBarcode() {
        Product p = null;
        try {
            p = this.productController.findByBarcode(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        assertNull(p);
    }
}
