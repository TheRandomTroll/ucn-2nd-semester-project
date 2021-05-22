package db.interfaces;

import exceptions.DataAccessException;
import models.Address;
import models.Customer;

public interface CustomerDBIF {
    Customer findByPhoneNo(String phoneNo) throws DataAccessException;

    int createCustomer(String name, String phoneNo, String email, int addressId) throws DataAccessException;
}
