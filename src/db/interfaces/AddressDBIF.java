package db.interfaces;

import exceptions.DataAccessException;
import models.Address;

public interface AddressDBIF {
    int createAddress(String street, String streetNumber, String floor, String city, String postalCode) throws DataAccessException;
    Address findById(int id) throws DataAccessException;
}
