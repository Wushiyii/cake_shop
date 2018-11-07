package com.jesse.onecake;

public enum OrderStatusEnum {
    TO_BE_PAID("TO_BE_PAID"),PAID("CHECKED"),CANCELED("CANCELED"),
    NOT_RECEIVED("NOT_RECEIVE"),DELIVERING("DELIVERING"),RECEIVED("RECEIVED")
    ;
    String value;
    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
