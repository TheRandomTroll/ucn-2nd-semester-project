package models;

import models.enums.PaymentType;

import java.sql.Date;

public class ShoppingList {
    private Date creationDate;
    private Order order;
    private PaymentType paymentType;

    public ShoppingList(Date creationDate, Order order, PaymentType paymentType) {
        this.creationDate = creationDate;
        this.order = order;
        this.paymentType = paymentType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
