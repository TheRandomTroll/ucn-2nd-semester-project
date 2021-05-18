package models;

import models.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private String orderNumber;
    private Customer customer;
    private OrderStatus status;
    private Address invoiceAddress;
    private Address deliveryAddress;
    private List<OrderLine> orderLines;

    public Order(int id, String orderNumber, Customer customer, OrderStatus status, Address invoiceAddress, Address deliveryAddress) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.status = status;
        this.invoiceAddress = invoiceAddress;
        this.deliveryAddress = deliveryAddress;
        this.orderLines = new ArrayList<>();
    }

    public Order(Customer customer) {
        this.customer = customer;
        this.status = OrderStatus.DRAFT;
        this.invoiceAddress = customer.getAddress();
        this.deliveryAddress = customer.getAddress();
        this.orderLines = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Address getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(Address invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
    public void addOrderLine(Product p, int qty) {
        OrderLine ol = new OrderLine(p, qty);
        this.orderLines.add(ol);
    }
}
