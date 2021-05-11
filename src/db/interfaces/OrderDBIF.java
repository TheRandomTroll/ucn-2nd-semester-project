package db.interfaces;

import models.Customer;
import models.Order;

public interface OrderDBIF {
    int createOrder(Order o);
}
