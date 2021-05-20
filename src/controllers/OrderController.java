package controllers;

import db.OrderDB;
import db.interfaces.OrderDBIF;
import exceptions.DataAccessException;
import models.Customer;
import models.Order;
import models.Product;
import models.enums.OrderStatus;

import java.sql.Date;
import java.time.LocalDate;

public class OrderController {
    private Order order;
    private OrderDBIF orderDB;

    public OrderController() throws DataAccessException {
        this.orderDB = new OrderDB();
    }

    public Order findCustomer(String phoneNo) throws DataAccessException {
        Customer c = new CustomerController().findByPhoneNo(phoneNo);
        order = new Order(c);
        this.orderDB.createOrder(order);
        return order;
    }

    public void addProduct(int barcode, int qty) throws DataAccessException {
        Product p = new ProductController().findByBarcode(barcode);
        this.order.addOrderLine(p, qty);
    }

    public int finishSale() throws DataAccessException {
        Date date = Date.valueOf(LocalDate.now());
        this.order.setStatus(OrderStatus.ALLOCATED);
        return orderDB.updateOrder(order);
    }
}
