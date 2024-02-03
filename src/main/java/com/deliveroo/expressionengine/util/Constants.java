package com.deliveroo.expressionengine.util;

public class Constants {
    public static final String SPACE_REGEX = "\\s";
    public static final String ASTERISK = "*";
    public static final String EMPTY_SPACE = " ";
    public static final String FORWARD_SLASH = "/";
    public static final String NON_NEGATIVE_NUMBERS_REGEX = "^\\d+$";
    public static final String HYPHEN = "-";
    public static final String COMMA = ",";

    private Constants() throws IllegalAccessException {
        throw new IllegalAccessException("Util classes should not invoked via constructor");
    }
}
