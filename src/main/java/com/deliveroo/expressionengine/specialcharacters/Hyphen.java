package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.deliveroo.expressionengine.util.Constants.EMPTY_SPACE;
import static com.deliveroo.expressionengine.util.Constants.HYPHEN;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Hyphen extends BaseSpecialCharacter {

    public Hyphen(final Token token) {
        super(token);
    }

    @Override
    public String getExplanation() {
        String[] hyphenParts = token.getValue().split(HYPHEN);
        if (hyphenParts.length != 2 || parseInt(hyphenParts[0]) > parseInt(hyphenParts[1])) {
            throw new RuntimeException("hyphen sub-token numerical should be in the format of a-b and a < b");
        }
        validateDerivedValueRangeWithTokenMinimumAndMaximum(valueOf(hyphenParts[0]), valueOf(hyphenParts[1]));
        return IntStream.rangeClosed(parseInt(hyphenParts[0]), parseInt(hyphenParts[1]))
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }
}
