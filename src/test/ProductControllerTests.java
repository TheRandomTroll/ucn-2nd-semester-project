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
    public void setUp() throws DataAccessException {
        this.productController = new ProductController();
    }

    @Test
    public void testFindProductByBarcode() throws DataAccessException {
        Product p = this.productController.findByBarcode(93766442);

        assertEquals(p.getName(), "Dried Apple");
        assertEquals(p.getId(), 17);
    }

    @Test
    public void testFindProductByInvalidBarcode() throws DataAccessException {
        Product p = this.productController.findByBarcode(0);

        assertNull(p);
    }
}
