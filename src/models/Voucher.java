package models;

import java.sql.Date;

public class Voucher {
    private int id;
    private String code;
    private Date expirationDate;
    private double discount;

    public Voucher(String code, Date expirationDate, double discount) {
        this.code = code;
        this.expirationDate = expirationDate;
        this.setDiscount(discount);
    }

    public Voucher(int id, String code, Date expirationDate, double discount) {
        this.id = id;
        this.code = code;
        this.expirationDate = expirationDate;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if(discount < 0 || discount > 1) {
            throw new IllegalArgumentException("discount should be a value between 0 and 1.");
        }
        this.discount = discount;
    }
}
