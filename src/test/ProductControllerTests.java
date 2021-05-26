package test;

import controllers.CustomerController;
import controllers.ProductController;
import db.DBConnection;
import exceptions.DataAccessException;
import models.Customer;
import models.Product;
import org.junit.AfterClass;
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
        assertEquals(16, p.getId());
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

    @Test
    public void testCreateProduct() {
        try {
            Product p = new Product("test", 12345678, "test description", 7.99f, 5, 50, 50);
            int rows = this.productController.addProduct(p);

            assertEquals(1, rows);

            p = this.productController.findByBarcode(12345678);
            assertEquals("test", p.getName());

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateProduct() {
        try {
            Product p = this.productController.findByBarcode(12345678);
            p.setName("updated test");
            int rows = this.productController.updateProduct(p);

            assertEquals(1, rows);

            p = this.productController.findByBarcode(12345678);
            assertEquals("updated test", p.getName());

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteProduct() {
        try {
            Product p = this.productController.findByBarcode(12345678);
            int rows = this.productController.deleteProduct(p.getId());

            assertEquals(1, rows);

            p = this.productController.findByBarcode(12345678);
            assertNull(p);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void cleanUpWhenFinish() {
        try {
            ProductController pc = new ProductController();
            Product p = pc.findByBarcode(12345678);
            int numDeleted = pc.deleteProduct(p.getId());
            // Assert
            assertEquals("One row deleted", 1, numDeleted);
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            DBConnection.closeConnection();
        }

    }
}
