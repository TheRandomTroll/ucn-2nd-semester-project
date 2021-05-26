package controllers;

import db.CustomerDB;
import db.interfaces.CustomerDBIF;
import exceptions.DataAccessException;
import models.Address;
import models.Customer;

public class CustomerController {
    private final CustomerDBIF customerDB;
    private final AddressController addressController;

    public CustomerController() throws DataAccessException {
        this.customerDB = new CustomerDB();
        this.addressController = new AddressController();
    }

    public Customer findByPhoneNo(String phoneNo) throws DataAccessException {
        return this.customerDB.findByPhoneNo(phoneNo);
    }

    public int createCustomer(Customer c) throws DataAccessException {
        return this.customerDB.createCustomer(c);
    }

    public int updateCustomer(Customer c) throws DataAccessException {
        return this.customerDB.updateCustomer(c);
    }

    public int deleteCustomer(Customer c) throws DataAccessException {
        return this.customerDB.deleteCustomer(c);
    }

    public Address findAddressByData(Address a) throws DataAccessException {
        return this.addressController.findByData(a);
    }

    public Address findAddressById(int id) throws DataAccessException {
        return this.addressController.findById(id);
    }

    public void createAddress(Address a) throws DataAccessException {
        this.addressController.createAddress(a);
    }
}
