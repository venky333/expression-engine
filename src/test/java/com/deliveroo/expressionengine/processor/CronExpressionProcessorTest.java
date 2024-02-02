package com.deliveroo.expressionengine.processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronExpressionProcessorTest {

    private static final String VALID_EXPRESSION = "* 0 1,15 * 1-5 /usr/bin/find";
    private static final String INVALID_EXPRESSION = "*/15 0 1,15 *";

    @Test
    void testProcessValidExpression() {
        // valid expression with all required fields
        new CronExpressionProcessor(VALID_EXPRESSION);
        // no exception should be thrown
    }

    @Test
    void testProcessInvalidExpression() {
        // invalid expression with missing command field
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(INVALID_EXPRESSION));
    }

    @Test
    void testProcessExpressionWithInvalidToken() {
        // invalid token in the minute field
        String invalidMinuteExpression = "60 0 1 * 1 /usr/bin/find";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteExpression));
    }

    @Test
    void testProcessExpressionWithLessThanSixTokens() {
        // expression with fewer than 6 tokens
        String invalidExpression = "*/15 0 1 * 1";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidExpression));
    }

    @Test
    void processExpressionWithExtraTokens() {
        // Expression with more than 6 tokens
        String invalidExpression = "*/15 0 1 * 1 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidExpression));
    }

    @Test
    void testProcessExpressionWithInvalidMinuteAndExtraTokens() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "60 0 1 * 1 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }

    @Test
    void testProcessExpressionWithValidMinuteAndInvalidHour() {
        // valid minute but invalid token in the hour field
        String invalidHourExpression = "*/15 25 1 * 1 /usr/bin/find";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidHourExpression));
    }
}
