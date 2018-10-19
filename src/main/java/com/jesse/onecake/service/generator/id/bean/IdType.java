package com.jesse.onecake.service.generator.id.bean;

public enum IdType {
    SECONDS("seconds"),
    MILLISECONDS("milliseconds");

    private String name;

    private IdType(String name) {
        this.name = name;
    }

    public long value() {
        switch(this) {
        case SECONDS:
            return 0L;
        case MILLISECONDS:
            return 1L;
        default:
            return 0L;
        }
    }

    public String toString() {
        return this.name;
    }

    public static IdType parse(String name) {
        if (SECONDS.name.equals(name)) {
            return SECONDS;
        } else if (MILLISECONDS.name.equals(name)) {
            return MILLISECONDS;
        } else {
            throw new IllegalArgumentException("Illegal IdType name <[" + name + "]>, available names are seconds and milliseconds");
        }
    }

    public static IdType parse(long type) {
        if (type == 1L) {
            return MILLISECONDS;
        } else if (type == 0L) {
            return SECONDS;
        } else {
            throw new IllegalArgumentException("Illegal IdType value <[" + type + "]>, available values are 0 (for seconds) and 1 (for milliseconds)");
        }
    }
}
