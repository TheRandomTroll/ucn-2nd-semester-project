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

    public int createCustomer(String name, String phoneNo, String email, Address a) throws DataAccessException {
        return this.customerDB.createCustomer(name, phoneNo, email, a.getId());
    }

    public Address findAddressByData(Address a) throws DataAccessException {
        return this.addressController.findByData(a);
    }

    public void createAddress(Address a) throws DataAccessException {
        this.addressController.createAddress(a);
    }

    public int updateCustomer(String name, String phoneNumber, String email, Address a) throws DataAccessException {
        return this.customerDB.updateCustomer(name, phoneNumber, email, a.getId());
    }
}
