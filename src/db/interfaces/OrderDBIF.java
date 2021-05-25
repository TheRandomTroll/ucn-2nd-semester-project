package db.interfaces;

import exceptions.DataAccessException;
import models.Order;
import models.enums.OrderStatus;

import java.util.List;

public interface OrderDBIF {

    List<Order> getFinishedOrders() throws DataAccessException;

    int createOrder(Order o) throws DataAccessException;

    int saveOrder(Order o) throws DataAccessException;

    int updateOrderStatus(int id, OrderStatus status) throws DataAccessException;
}
