package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import static com.deliveroo.expressionengine.util.Constants.*;

public abstract class BaseSpecialCharacter {
    public Token token;

    public BaseSpecialCharacter(final Token token) {
        this.token = token;
    }

    public static BaseSpecialCharacter getBaseSpecialCharacterByToken(final Token token) {
        if (token.getValue().equalsIgnoreCase(ASTERISK))
            return new Asterisk(token);
        if (token.getValue().contains(FORWARD_SLASH))
            return new Slash(token);
        if (token.getValue().matches(NON_NEGATIVE_NUMBERS_REGEX))
            return new Number(token);
        if (token.getValue().contains(HYPHEN))
            return new Hyphen(token);
        throw new RuntimeException(token.getValue() + " token is not supported now");
    }

    public abstract String getExplanation();

    public void validateDerivedValueRangeWithTokenMinimumAndMaximum(final Integer firstValue, final Integer secondValue) {
        if (firstValue < this.token.getMinimum() || firstValue > this.token.getMaximum() || secondValue < this.token.getMinimum() || secondValue > this.token.getMaximum())
            throw new RuntimeException("given values are not in expected limits. minimum expected value: " + this.token.getMinimum() + ", maximum expected value: " + this.token.getMaximum() + " and given values are first: " + firstValue + ", second value: " + secondValue);
    }
}
