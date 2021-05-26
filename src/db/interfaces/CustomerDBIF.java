package db.interfaces;

import exceptions.DataAccessException;
import models.Customer;

public interface CustomerDBIF {
    Customer findByPhoneNo(String phoneNo) throws DataAccessException;

    Customer findById(int id) throws DataAccessException;

    int createCustomer(Customer c) throws DataAccessException;

    int updateCustomer(Customer c) throws DataAccessException;

    int deleteCustomer(Customer c) throws DataAccessException;
}
