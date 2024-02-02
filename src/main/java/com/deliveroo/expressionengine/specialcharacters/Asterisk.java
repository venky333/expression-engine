package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.deliveroo.expressionengine.util.Constants.EMPTY_SPACE;

public class Asterisk extends BaseSpecialCharacter {

    public Asterisk(final Token token) {
        super(token);
    }

    @Override
    public String getExplanation() {
        return IntStream.rangeClosed(token.getMinimum(), token.getMaximum()).boxed()
                .collect(Collectors.toList())
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }
}
