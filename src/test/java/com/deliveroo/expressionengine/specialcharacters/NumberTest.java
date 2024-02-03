package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {

    @Test
    void testGetExplanation() {
        // given
        Token token = new Token("20", 0, 59);
        Number number = new Number(token);

        // when
        String explanation = number.getExplanation();

        // then
        assertEquals("20", explanation);
    }

    @Test
    void testGetExplanationForTokenValueGreaterThanMaximum() {
        // given
        Token token = new Token("60 * * * * /usr/bin/find", 0, 59);
        Number number = new Number(token);

        assertThrows(RuntimeException.class, number::getExplanation);
    }

    @Test
    void testGetExplanationForTokenValueLessThanMinimum() {
        // given
        Token token = new Token("2 * * * * /usr/bin/find", 6, 59);
        Number number = new Number(token);

        assertThrows(RuntimeException.class, number::getExplanation);
    }

    @Test
    void testGetExplanationForTokenValueNegative() {
        // given
        Token token = new Token("-2 * * * * /usr/bin/find", 6, 59);
        Number number = new Number(token);

        assertThrows(RuntimeException.class, number::getExplanation);
    }

    @Test
    void testGetExplanationForTokenValueDecimal() {
        // given
        Token token = new Token("0.2 * * * * /usr/bin/find", 6, 59);
        Number number = new Number(token);

        assertThrows(RuntimeException.class, number::getExplanation);
    }
}
