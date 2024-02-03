package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HyphenTest {
    @Test
    void testGetExplanation() {
        // given
        Token token = new Token("1-5", 0, 59);
        Hyphen hyphen = new Hyphen(token);

        // when
        String explanation = hyphen.getExplanation();

        // then
        assertEquals("1 2 3 4 5", explanation);
    }

    @Test
    void testGetExplanationWithFirstPartGreaterThanSecondPart() {
        // given
        Token token = new Token("10-5", 0, 59);
        Hyphen hyphen = new Hyphen(token);

        // then
        assertThrows(RuntimeException.class, hyphen::getExplanation);
    }

    @Test
    void testGetExplanationWithFirstPartGreaterThanMaximum() {
        // given
        Token token = new Token("90-5", 0, 59);
        Hyphen hyphen = new Hyphen(token);

        // then
        assertThrows(RuntimeException.class, hyphen::getExplanation);
    }

    @Test
    void testGetExplanationWithSecondPartLessthanThanMaximum() {
        // given
        Token token = new Token("5-5", 10, 59);
        Hyphen hyphen = new Hyphen(token);

        // then
        assertThrows(RuntimeException.class, hyphen::getExplanation);
    }

    @Test
    void testGetExplanationWithSecondPartNotNumber() {
        // given
        Token token = new Token("5-*", 10, 59);
        Hyphen hyphen = new Hyphen(token);

        // then
        assertThrows(RuntimeException.class, hyphen::getExplanation);
    }
}
