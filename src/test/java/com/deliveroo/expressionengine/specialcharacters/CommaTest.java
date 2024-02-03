package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommaTest {
    @Test
    void testGetExpression() {
        // given
        Token token = new Token("1,5", 0, 59);
        Comma comma = new Comma(token);

        // when
        String expression = comma.getExplanation();

        // then
        assertEquals("1 5", expression);
    }

    @Test
    void testGetExpressionUnsortedValues() {
        // given
        Token token = new Token("1,5,20,3,2", 0, 59);
        Comma comma = new Comma(token);

        // when
        String expression = comma.getExplanation();

        // then
        assertEquals("1 2 3 5 20", expression);
    }

    @Test
    void testGetExpressionWithDuplicates() {
        // given
        Token token = new Token("1,5,20,3,2,1", 0, 59);
        Comma comma = new Comma(token);

        // when
        String expression = comma.getExplanation();

        // then
        assertEquals("1 2 3 5 20", expression);
    }

    @Test
    void testGetExpressionUnsortedValuesWithSpaces() {
        // given
        Token token = new Token("1,5,20, 3, 2", 0, 59);
        Comma comma = new Comma(token);

        // when
        String expression = comma.getExplanation();

        // then
        assertEquals("1 2 3 5 20", expression);
    }

    @Test
    void testGetExpressionWithAnyOneValueGreaterThanMaximum() {
        // given
        Token token = new Token("1,5,20,300,2", 0, 59);
        Comma comma = new Comma(token);

        // then
        assertThrows(RuntimeException.class, comma::getExplanation);
    }

    @Test
    void testGetExpressionWithAnyOneValueLessThanMinimum() {
        // given
        Token token = new Token("1,-5,20,300,2", 30, 59);
        Comma comma = new Comma(token);

        // then
        assertThrows(RuntimeException.class, comma::getExplanation);
    }
}
