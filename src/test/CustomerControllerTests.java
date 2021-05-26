package test;

import controllers.CustomerController;
import db.DBConnection;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CustomerControllerTests {
    private CustomerController customerController;

    @Before
    public void setUp() {
        try {
            this.customerController = new CustomerController();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindingByPhoneNumber() {
        try {
            Customer c = this.customerController.findByPhoneNo("+385919826498");
            assertEquals("Mihail Veljkovic", c.getName());
            assertEquals(2, c.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindingByInvalidPhoneNumber()  {
        try {
            Customer c = this.customerController.findByPhoneNo("+385000000000");
            assertNull(c);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommunicationWithAddressController() {
        try {
            Customer c = this.customerController.findByPhoneNo("+385919826498");
            Address a = this.customerController.findAddressByData(c.getAddress());
            assertEquals("Rovinjska", a.getStreetName());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateCustomer() {
        try {
            Address a = this.customerController.findAddressById(1);
            Customer c = new Customer("Test Customer", "+385123456789", "test@test.com", a);
            int rows = this.customerController.createCustomer(c);

            assertEquals(1, rows);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateCustomer() {
        try {
            Customer c = this.customerController.findByPhoneNo("+385919826498");
            c.setEmail("test2@test.com");
            c.setPhoneNumber("+359123456789");
            int rows = this.customerController.updateCustomer(c);

            assertEquals(1, rows);

            c = this.customerController.findByPhoneNo("+359123456789");
            assertEquals("test2@test.com", c.getEmail());

            c = this.customerController.findByPhoneNo("+385919826498");
            assertNull(c);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteCustomer() {
        try {
            Customer c = this.customerController.findByPhoneNo("+359123456789");
            int rows = this.customerController.deleteCustomer(c);

            assertEquals(1, rows);

            c = this.customerController.findByPhoneNo("+359123456789");

            assertNull(c);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void cleanUpWhenFinish() {
        try {
            CustomerController cc = new CustomerController();
            Customer c = cc.findByPhoneNo("+385123456789");
            int numDeleted = cc.deleteCustomer(c);
            // Assert
            assertEquals("One row deleted", 1, numDeleted );
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            DBConnection.closeConnection();
        }

    }
}
