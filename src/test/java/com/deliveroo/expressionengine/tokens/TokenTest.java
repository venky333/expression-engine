package com.deliveroo.expressionengine.tokens;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class TokenTest {

    @Test
    void testConstructor() {
        // given
        String value = anyString();
        Integer minimum = anyInt();
        Integer maximum = anyInt();

        // when
        Token token = new Token(value, minimum, maximum);

        // then
        assertNotNull(token);
        assertEquals(value, token.getValue());
        assertEquals(minimum, token.getMinimum());
        assertEquals(maximum, token.getMaximum());
    }

    @Test
    void testGetters() {
        // given
        String value = anyString();
        Integer minimum = anyInt();
        Integer maximum = anyInt();

        // when
        Token token = new Token(value, minimum, maximum);

        // then
        assertEquals(value, token.getValue());
        assertEquals(minimum, token.getMinimum());
        assertEquals(maximum, token.getMaximum());
    }

    @Test
    void testSetters() {
        // given
        String value = anyString();
        Integer minimum = anyInt();
        Integer maximum = anyInt();

        // when
        Token token = new Token(value, minimum, maximum);
        token.setMinimum(10);
        token.setMaximum(100);

        // then
        assertNotNull(token);
        assertEquals(value, token.getValue());
        assertEquals(10, token.getMinimum());
        assertEquals(100, token.getMaximum());
    }

    @Test
    void testEqualsAndHashCode() {
        // when
        Token tokenOne = new Token("*", 0, 59);
        Token tokenTwo = new Token("*", 0, 59);

        // then
        assertEquals(tokenOne, tokenTwo);
        assertEquals(tokenOne.hashCode(), tokenTwo.hashCode());
    }

    @Test
    void testToString() {
        // when
        Token token = new Token("*", 0, 59);
        String expectedToString = "Token(value=*, minimum=0, maximum=59)";

        // then
        assertEquals(expectedToString, token.toString());
    }
}
