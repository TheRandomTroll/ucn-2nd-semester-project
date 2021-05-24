package db.interfaces;

import exceptions.DataAccessException;
import models.Customer;

public interface CustomerDBIF {
    Customer findByPhoneNo(String phoneNo) throws DataAccessException;

    Customer findById(int id) throws DataAccessException;

    int createCustomer(String name, String phoneNo, String email, int addressId) throws DataAccessException;

    int updateCustomer(String name, String phoneNo, String email, int addressId) throws DataAccessException;
}
