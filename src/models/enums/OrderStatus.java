package models.enums;

public enum OrderStatus {
    DRAFT,
    ALLOCATED,
    PACKED,
    SHIPPED,
    DELIVERED,
    NOT_ACCEPTED,
    CANCELLED;

    public int getValue() {
        return ordinal() + 1;
    }
}
