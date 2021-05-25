package db.interfaces;

import exceptions.DataAccessException;
import models.Courier;

public interface CourierBatchDBIF {
    Courier dispatchOrder(int orderId) throws DataAccessException;
}
