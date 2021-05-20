package controllers;

import db.CustomerDB;
import db.interfaces.CustomerDBIF;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;

public class CustomerController {
    private CustomerDBIF customerDB;
    private AddressController addressController;

    public CustomerController() throws DataAccessException {
        this.customerDB = new CustomerDB();
        this.addressController = new AddressController();
    }

    public Customer findByPhoneNo(String phoneNo) throws DataAccessException {
        return this.customerDB.findByPhoneNo(phoneNo);
    }

    public int createCustomer(String name, String phoneNo, Address a) throws DataAccessException {
        if(a.getId() == 0) {
            this.addressController.createAddress(a);
        }

        return this.customerDB.createCustomer(name, phoneNo, a.getId());
    }
}
