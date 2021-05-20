package models.enums;

public enum PaymentType {
    CASH_ON_DELIVERY,
    CARD,
    PAYPAL;

    public int getValue() {
        return ordinal() + 1;
    }
}
