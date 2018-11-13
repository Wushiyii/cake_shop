package com.jesse.onecake.enums;

public enum CakeEnum {
    NOT_ON_BANNER("false"),ON_BANNER("true"),
    NORMAL("normal"),DISABLED("disabled")
    ;
    String value;
    CakeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
