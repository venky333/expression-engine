package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import static com.deliveroo.expressionengine.util.Constants.ASTERISK;

public abstract class BaseSpecialCharacter {
    public Token token;

    public BaseSpecialCharacter(final Token token) {
        this.token = token;
    }

    public static BaseSpecialCharacter getBaseSpecialCharacterByToken(final Token token) {
        if (token.getValue().equalsIgnoreCase(ASTERISK))
            return new Asterisk(token);
        return null;
    }

    public abstract String getExplanation();
}
