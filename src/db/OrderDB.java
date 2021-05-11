package db;

import db.interfaces.OrderDBIF;
import models.Customer;
import models.Order;

public class OrderDB implements OrderDBIF {
    private static final String CREATE_ORDER_Q = "INSERT INTO Orders (";
    public OrderDB() {

    }

    @Override
    public int createOrder(Order o) {
        return 0;
    }
}
