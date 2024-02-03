package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.deliveroo.expressionengine.util.Constants.COMMA;
import static com.deliveroo.expressionengine.util.Constants.EMPTY_SPACE;

public class Comma extends BaseSpecialCharacter {
    public Comma(final Token token) {
        super(token);
    }

    @Override
    public String getExplanation() {
        String[] commaParts = token.getValue().split(COMMA);
        return Arrays.stream(commaParts)
                .map(String::trim)
                .map(Integer::parseInt)
                .peek(val -> {
                    if (val < token.getMinimum() || val > token.getMaximum())
                        throw new RuntimeException("given values is out of expected range of minimum value: " + token.getMinimum() + ", maximum value: " + token.getMaximum());
                })
                .sorted()
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }
}
