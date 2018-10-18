package com.jesse.onecake.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class StringEscapeEditor extends PropertyEditorSupport {
    public StringEscapeEditor() {
    }

    @Override
    public String getAsText() {
        Object value = this.getValue();
        return value == null ? "" : value.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            this.setValue(null);
        } else {
            String value = clearXss(text);
            this.setValue(value);
        }
    }

    public static String clearXss(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        } else {
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            value = value.replaceAll("'", "&#39;");
            value = value.replaceAll("eval\\((.*)\\)", "");
            value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
            value = value.replaceAll("script", "");
            return value;
        }

    }


}
