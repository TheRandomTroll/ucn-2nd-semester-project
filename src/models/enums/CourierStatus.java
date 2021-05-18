package models.enums;

public enum CourierStatus {
    AVAILABLE,
    DELIVERING,
    ON_VACATION;

    public int getValue() {
        return ordinal() + 1;
    }
}
