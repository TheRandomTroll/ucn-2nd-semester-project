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
}
