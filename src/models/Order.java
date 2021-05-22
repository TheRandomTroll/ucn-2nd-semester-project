package models;

import models.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Order {
    private int id;
    private String orderNumber;
    private double totalPrice;
    private Customer customer;
    private OrderStatus status;
    private Address invoiceAddress;
    private Address deliveryAddress;
    private List<OrderLine> orderLines;
    private Voucher appliedVoucher;

    public Order(int id, String orderNumber, Customer customer, OrderStatus status, Address invoiceAddress, Address deliveryAddress) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.totalPrice = 0;
        this.customer = customer;
        this.status = status;
        this.invoiceAddress = invoiceAddress;
        this.deliveryAddress = deliveryAddress;
        this.orderLines = new ArrayList<>();
    }

    public Order(Customer customer) {
        this.customer = customer;
        this.orderNumber = "ON" + (100000 + id);
        this.totalPrice = 0;
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
        this.setOrderNumber("ON" + (100000 + id));
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDiscountedPrice() {
        if(appliedVoucher == null) {
            return totalPrice;
        }

        return (1 - appliedVoucher.getDiscount()) * totalPrice;
    }

    public void updateTotalPrice() {
        double total = 0;
        for(OrderLine ol : orderLines) {
            total += ol.getQuantity() * ol.getProduct().getPrice();
        }

        this.totalPrice = total;
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Voucher getAppliedVoucher() {
        return appliedVoucher;
    }

    public void setAppliedVoucher(Voucher appliedVoucher) {
        this.appliedVoucher = appliedVoucher;
    }

    public void addOrderLine(Product p, int qty) {
        OptionalInt productExists = hasProductInOrderLines(p);
        if(productExists.isPresent()) {
            int index = productExists.getAsInt();
            OrderLine ol = orderLines.get(index);
            orderLines.get(index).setQuantity(ol.getQuantity() + qty);
        } else {
            OrderLine ol = new OrderLine(p, qty);
            this.orderLines.add(ol);
        }

        this.updateTotalPrice();
    }

    private OptionalInt hasProductInOrderLines(Product p) {
        return IntStream.range(0, orderLines.size())
                .filter(x -> orderLines.get(x).getProduct().getBarcode() == p.getBarcode())
                .findFirst();
    }
}
