package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlashTest {

    @Test
    void testGetExplanationWithAsteriskInPartOneOfSlash() {
        // given
        Token token = new Token("*/15", 0, 59);
        Slash slash = new Slash(token);

        // when
        String explanation = slash.getExplanation();

        // then
        assertEquals("0 15 30 45", explanation);
    }

    @Test
    void testGetExplanationWithAsteriskInPartOneOfSlashAndPartTwoWithGreaterThanMaxValue() {
        // given
        Token token = new Token("*/60", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithAsteriskInPartOneAndTwo() {
        // given
        Token token = new Token("*/*", 0, 24);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithNumberInPartOneOfSlash() {
        // given
        Token token = new Token("2/15", 0, 59);
        Slash slash = new Slash(token);

        // when
        String explanation = slash.getExplanation();

        // then
        assertEquals("2 17 32 47", explanation);
    }

    @Test
    void testGetExplanationWithNumberInPartOneOfSlashAndPartTwoWithGreaterThanMaxValue() {
        // given
        Token token = new Token("2/60", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithHyphenInPartOneOfSlash() {
        // given
        Token token = new Token("1-16/15", 0, 59);
        Slash slash = new Slash(token);

        // when
        String explanation = slash.getExplanation();

        // then
        assertEquals("1 16", explanation);
    }

    @Test
    void testGetExplanationWithHyphenValuesReversedInPartOneOfSlash() {
        // given
        Token token = new Token("16-1/15", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithHyphenValueGreaterThanMaxInPartOneOfSlash() {
        // given
        Token token = new Token("1-60/15", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithHyphenInPartOneOfSlashAndPartTwoWithGreaterThanMaxValueForMinute() {
        // given
        Token token = new Token("1-16/60", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithCommaInPartOneOfSlash() {
        // given
        Token token = new Token("1,16/15", 0, 59);
        Slash slash = new Slash(token);

        // when
        String explanation = slash.getExplanation();

        // then
        assertEquals("1 16 31 46", explanation);
    }

    @Test
    void testGetExplanationWithCommaValuesReversedInPartOneOfSlash() {
        // given
        Token token = new Token("16,1/15", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithCommaValueGreaterThanMaxInPartOneOfSlash() {
        // given
        Token token = new Token("1,60/15", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithCommaInPartOneOfSlashAndPartTwoWithGreaterThanMaxValue() {
        // given
        Token token = new Token("1-16/60", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithInvalidTokenValue() {
        // given
        Token token = new Token("inv-alid", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }

    @Test
    void testGetExplanationWithInvalidNumericalTokenValue() {
        // given
        Token token = new Token("invalid/15", 0, 59);
        Slash slash = new Slash(token);

        // then
        assertThrows(RuntimeException.class, slash::getExplanation);
    }
}
