package db.interfaces;

import exceptions.DataAccessException;
import models.Customer;
import models.Order;
import models.Product;

public interface OrderDBIF {
    int createOrder(Order o) throws DataAccessException;

    int saveOrder(Order o) throws DataAccessException;
}
