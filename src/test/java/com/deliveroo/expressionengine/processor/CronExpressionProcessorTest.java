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
    void testProcessExpressionWithInvalidMinuteAndExtraTokens() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "60 0 1 * 1 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }

    @Test
    void testProcessValidExpressionForMinute() {
        // valid expression with all required fields
        new CronExpressionProcessor(VALID_EXPRESSION);
        // no exception should be thrown
    }

    @Test
    void testProcessValidExpressionForMinuteWithSlash() {
        // valid expression with all required fields
        new CronExpressionProcessor("*/15 0 1 * 1 /usr/bin/find");
        // no exception should be thrown
    }

    @Test
    void testProcessValidExpressionForMinuteWithHyphen() {
        // valid expression with all required fields
        new CronExpressionProcessor("1-5/15 0 1 * 1 /usr/bin/find");
        // no exception should be thrown
    }

    @Test
    void testProcessValidExpressionForMinuteWithComma() {
        // valid expression with all required fields
        new CronExpressionProcessor("1,5/15 0 1 * 1 /usr/bin/find");
        // no exception should be thrown
    }

    @Test
    void testProcessExpressionWithInvalidMinute() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "60 0 1 * 1 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }

    @Test
    void testProcessValidExpressionForHour() {
        // valid expression with all required fields
        new CronExpressionProcessor("15 0 1 * 1 /usr/bin/find new");
        // no exception should be thrown
    }

    @Test
    void testProcessExpressionWithInvalidHour() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "15 24 1 * 1 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }

    @Test
    void testProcessValidExpressionForDayOfMonth() {
        // valid expression with all required fields
        new CronExpressionProcessor("15 0 1 * 1 /usr/bin/find new");
        // no exception should be thrown
    }

    @Test
    void testProcessExpressionWithInvalidDayOfMonth() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "15 20 32 * 1 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }

    @Test
    void testProcessValidExpressionForMonth() {
        // valid expression with all required fields
        new CronExpressionProcessor("15 0 1 * 1 /usr/bin/find new");
        // no exception should be thrown
    }

    @Test
    void testProcessExpressionWithInvalidMonth() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "15 20 1 13 1 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }

    @Test
    void testProcessValidExpressionForDayOfWeek() {
        // valid expression with all required fields
        new CronExpressionProcessor("15 0 1 * 1 /usr/bin/find new");
        // no exception should be thrown
    }

    @Test
    void testProcessExpressionWithInvalidDayOfWeek() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "15 24 1 * 8 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }

    @Test
    void testProcessValidExpressionForExtraCommands() {
        // valid expression with all required fields
        new CronExpressionProcessor("15 0 1 * 1 /usr/bin/find new");
        // no exception should be thrown
    }

    @Test
    void testProcessExpressionWithUnknownSpecialCharacter() {
        // invalid token in the minute field and extra tokens
        String invalidMinuteAndExtraTokensExpression = "15 > 1 * 8 /usr/bin/find new";
        assertThrows(RuntimeException.class, () -> new CronExpressionProcessor(invalidMinuteAndExtraTokensExpression));
    }
}
