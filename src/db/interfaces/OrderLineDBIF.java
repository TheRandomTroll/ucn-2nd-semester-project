package db.interfaces;

import exceptions.DataAccessException;
import models.Order;
import models.OrderLine;

public interface OrderLineDBIF {
    int createOrderLine(Order o, OrderLine ol) throws DataAccessException;
}
