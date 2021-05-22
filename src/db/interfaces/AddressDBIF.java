package db.interfaces;

import exceptions.DataAccessException;
import models.Address;

public interface AddressDBIF {
    int createAddress(Address a) throws DataAccessException;
    Address findById(int id) throws DataAccessException;
    Address findByData(Address a) throws DataAccessException;
}
