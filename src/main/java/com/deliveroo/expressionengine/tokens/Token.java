package com.deliveroo.expressionengine.tokens;

import com.deliveroo.expressionengine.specialcharacters.BaseSpecialCharacter;
import lombok.Data;

import java.util.Objects;

@Data
public class Token {
    String value;
    Integer minimum;
    Integer maximum;

    public Token(final String value, final Integer minimum, final Integer maximum) {
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public String process() {
        return Objects.requireNonNull(BaseSpecialCharacter.getBaseSpecialCharacterByToken(this)).getExplanation();
    }
}
