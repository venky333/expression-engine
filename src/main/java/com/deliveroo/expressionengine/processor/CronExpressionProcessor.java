package com.deliveroo.expressionengine.processor;

import com.deliveroo.expressionengine.tokens.Token;

import static com.deliveroo.expressionengine.util.Constants.SPACE_REGEX;

public class CronExpressionProcessor {
    public CronExpressionProcessor(final String expression) {
        this.process(expression);
    }

    public void process(final String expression) {
        String[] tokens = expression.split(SPACE_REGEX);
        if (tokens.length < 6)
            throw new RuntimeException("cron expression should have 5 time fields (minute, hour, day of month, month, and day of week) and a command");

        System.out.println(new Token(tokens[0], 0, 59).process());
    }
}
