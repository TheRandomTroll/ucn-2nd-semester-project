package controllers;

import db.AddressDB;
import db.interfaces.AddressDBIF;
import exceptions.DataAccessException;
import models.Address;

public class AddressController {
    private AddressDBIF addressDB;

    public AddressController() throws DataAccessException {
        this.addressDB = new AddressDB();
    }

    public int createAddress(Address a) throws DataAccessException {
        return this.addressDB.createAddress(a);
    }
    public Address findById(int id) throws DataAccessException {
        return this.addressDB.findById(id);
    }

    public Address findByData(Address a) throws DataAccessException {
        return this.addressDB.findByData(a);
    }

    public void removeById(int id) throws DataAccessException {
        addressDB.removeAddress(id);
    }

    public void editStreet(int id, String source) throws DataAccessException {
        addressDB.changeStreet(id, source);
    }
}
