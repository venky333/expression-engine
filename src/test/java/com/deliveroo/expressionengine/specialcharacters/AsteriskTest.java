package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsteriskTest {
    @Test
    void getExplanationInRange() {
        // given
        Token token = new Token("*", 1, 5);
        Asterisk asterisk = new Asterisk(token);

        // when
        String explanation = asterisk.getExplanation();

        // then
        assertEquals("1 2 3 4 5", explanation);
    }

    @Test
    void getExplanationSingleValue() {
        // given
        Token token = new Token("*", 7, 7);
        Asterisk asterisk = new Asterisk(token);

        // when
        String explanation = asterisk.getExplanation();

        // then
        assertEquals("7", explanation);
    }

    @Test
    void getExplanationEmptyRange() {
        // given
        Token token = new Token("*", 10, 5);
        Asterisk asterisk = new Asterisk(token);

        // when
        String explanation = asterisk.getExplanation();

        // then
        assertEquals("", explanation);
    }

    @Test
    void getExplanationNegativeRange() {
        // given
        Token token = new Token("*", -2, 2);
        Asterisk asterisk = new Asterisk(token);

        // when
        String explanation = asterisk.getExplanation();

        // then
        assertEquals("-2 -1 0 1 2", explanation);
    }

    @Test
    void getExplanationZeroRange() {
        // given
        Token token = new Token("*", 0, 0);
        Asterisk asterisk = new Asterisk(token);

        // when
        String explanation = asterisk.getExplanation();

        // then
        assertEquals("0", explanation);
    }
}
