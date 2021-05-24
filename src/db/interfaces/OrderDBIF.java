package db.interfaces;

import exceptions.DataAccessException;
import models.Order;

import java.util.List;

public interface OrderDBIF {
    List<Order> getOrders() throws DataAccessException;

    int createOrder(Order o) throws DataAccessException;

    int saveOrder(Order o) throws DataAccessException;
}
