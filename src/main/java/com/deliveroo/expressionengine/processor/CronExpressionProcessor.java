package com.deliveroo.expressionengine.processor;

import com.deliveroo.expressionengine.tokens.Token;

import java.util.Arrays;

import static com.deliveroo.expressionengine.util.Constants.EMPTY_SPACE;
import static com.deliveroo.expressionengine.util.Constants.SPACE_REGEX;

public class CronExpressionProcessor {
    public CronExpressionProcessor(final String expression) {
        this.process(expression);
    }

    public void process(final String expression) {
        String[] tokens = expression.split(SPACE_REGEX);
        if (tokens.length < 6)
            throw new RuntimeException("cron expression should have 5 time fields (minute, hour, day of month, month, and day of week) and a command");

        StringBuilder sb = new StringBuilder();
        sb.append("minute        ").append(new Token(tokens[0], 0, 59).process()).append("\n")
                .append("hour          ").append(new Token(tokens[1], 0, 23).process()).append("\n")
                .append("day of month  ").append(new Token(tokens[2], 1, 31).process()).append("\n")
                .append("month         ").append(new Token(tokens[3], 1, 12).process()).append("\n")
                .append("day of week   ").append(new Token(tokens[4], 1, 7).process()).append("\n")
                .append("command       ").append(String.join(EMPTY_SPACE, Arrays.copyOfRange(tokens, 5, tokens.length)));

        // output
        System.out.println(sb);
    }
}
