package com.jesse.onecake.enums;

public enum OrderStatusEnum {
    TO_BE_PAID("TO_BE_PAID"),PAID("PAID"),CANCELED("CANCELED"),
    NOT_RECEIVED("NOT_RECEIVED"),DELIVERING("DELIVERING"),RECEIVED("RECEIVED"),PAIDCANCELED("PAIDCANCELED")
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
