package com.deliveroo.expressionengine.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.deliveroo.expressionengine.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstantsTest {

    @Test
    void testSpaceRegex() {
        assertEquals("\\s", SPACE_REGEX);
    }

    @Test
    void testAsterick() {
        assertEquals("*", ASTERISK);
    }

    @Test
    void testEmptySpace() {
        assertEquals(" ", EMPTY_SPACE);
    }

    @Test
    void testForwardSlash() {
        assertEquals("/", FORWARD_SLASH);
    }

    @Test
    void testNonNegativeNumbersRegex() {
        assertEquals("^\\d+$", NON_NEGATIVE_NUMBERS_REGEX);
    }

    @Test
    void testHyphen() {
        assertEquals("-", HYPHEN);
    }

    @Test
    void testComma() {
        assertEquals(",", COMMA);
    }

    @Test
    void testPrivateConstructor() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });
    }
}
