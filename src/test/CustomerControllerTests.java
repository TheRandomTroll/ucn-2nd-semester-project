package test;

import controllers.CustomerController;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;
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
}
