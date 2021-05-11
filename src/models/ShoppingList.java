package models;

import models.enums.PaymentType;

import java.sql.Date;

public class ShoppingList {
    private Date creationDate;
    private Order order;
    private Voucher appliedVoucher;
    private PaymentType paymentType;
}
