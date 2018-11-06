package com.jesse.onecake;

public enum OrderStatusEnum {
    TO_BE_CHECK("0"),CHECKED("1"),CANCELED("2");
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
