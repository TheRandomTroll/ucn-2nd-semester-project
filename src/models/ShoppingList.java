package models;

import models.enums.PaymentType;

import java.sql.Date;

public class ShoppingList {
    private Date creationDate;
    private Order order;
    private Voucher appliedVoucher;
    private PaymentType paymentType;

    public ShoppingList(Date creationDate, Order order, Voucher appliedVoucher, PaymentType paymentType) {
        this.creationDate = creationDate;
        this.order = order;
        this.appliedVoucher = appliedVoucher;
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

    public Voucher getAppliedVoucher() {
        return appliedVoucher;
    }

    public void setAppliedVoucher(Voucher appliedVoucher) {
        this.appliedVoucher = appliedVoucher;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
