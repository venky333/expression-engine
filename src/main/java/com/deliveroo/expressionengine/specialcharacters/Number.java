package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import static java.lang.Integer.valueOf;

public class Number extends BaseSpecialCharacter {

    public Number(final Token token) {
        super(token);
    }

    @Override
    public String getExplanation() {
        validateDerivedValueRangeWithTokenMinimumAndMaximum(valueOf(token.getValue()), valueOf(token.getValue()));
        return token.getValue();
    }
}
