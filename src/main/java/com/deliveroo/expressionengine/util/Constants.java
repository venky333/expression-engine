package com.deliveroo.expressionengine.util;

public class Constants {
    public static final String SPACE_REGEX = "\\s";
    public static final String ASTERISK = "*";
    public static final String EMPTY_SPACE = " ";

    private Constants() throws IllegalAccessException {
        throw new IllegalAccessException("Util classes should not invoked via constructor");
    }
}
