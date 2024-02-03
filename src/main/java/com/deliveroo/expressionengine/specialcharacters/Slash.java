package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.deliveroo.expressionengine.util.Constants.*;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Slash extends BaseSpecialCharacter {

    public Slash(final Token token) {
        super(token);
    }

    @Override
    public String getExplanation() {
        String[] subTokens = token.getValue().split(FORWARD_SLASH);

        validateSlashFormat(subTokens);

        Integer firstSubTokenValueOne = 0;
        Integer secondSubTokenValue = valueOf(subTokens[1]);

        if (subTokens[0].equalsIgnoreCase(ASTERISK)) {
            return handleAsteriskCase(secondSubTokenValue);
        }

        if (subTokens[0].matches(NON_NEGATIVE_NUMBERS_REGEX)) {
            firstSubTokenValueOne = valueOf(subTokens[0]);
            return handleNonNegativeNumbersCase(firstSubTokenValueOne, secondSubTokenValue);
        }

        if (subTokens[0].contains(HYPHEN)) {
            return handleHyphenCase(subTokens[0], secondSubTokenValue);
        }

        if (subTokens[0].contains(COMMA)) {
            return handleCommaCase(subTokens[0], secondSubTokenValue);
        }

        throw new RuntimeException("slash token is not recognized");
    }

    private void validateSlashFormat(String[] subTokens) {
        if (subTokens.length != 2 || !subTokens[1].trim().matches(NON_NEGATIVE_NUMBERS_REGEX)) {
            throw new RuntimeException("slash tokens should be in the format of a/b");
        }
    }

    private String handleAsteriskCase(Integer secondSubTokenValue) {
        Integer firstSubTokenValueOne = token.getMinimum();
        validateDerivedValueRangeWithTokenMinimumAndMaximum(firstSubTokenValueOne, secondSubTokenValue);

        return IntStream.rangeClosed(firstSubTokenValueOne, token.getMaximum())
                .filter(a -> a % secondSubTokenValue == 0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }

    private String handleNonNegativeNumbersCase(Integer firstSubTokenValueOne, Integer secondSubTokenValue) {
        validateDerivedValueRangeWithTokenMinimumAndMaximum(firstSubTokenValueOne, secondSubTokenValue);

        return IntStream.iterate(firstSubTokenValueOne, a -> a + secondSubTokenValue)
                .limit((token.getMaximum() - firstSubTokenValueOne) / secondSubTokenValue + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }

    private String handleHyphenCase(String hyphenPart, Integer secondSubTokenValue) {
        String[] subTokenHyphenSplitValues = hyphenPart.split(HYPHEN);
        if (subTokenHyphenSplitValues.length != 2)
            throw new RuntimeException("2 valid parts are required in hyphen separated values");
        validateHyphenCase(subTokenHyphenSplitValues, secondSubTokenValue);

        Integer subTokenHyphenSplitValueOneInteger = valueOf(subTokenHyphenSplitValues[0]);
        Integer subTokenHyphenSplitValueTwoInteger = valueOf(subTokenHyphenSplitValues[1]);

        return IntStream.iterate(subTokenHyphenSplitValueOneInteger, a -> a + secondSubTokenValue)
                .limit((subTokenHyphenSplitValueTwoInteger - subTokenHyphenSplitValueOneInteger) / secondSubTokenValue + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }

    private String handleCommaCase(String commaPart, Integer secondSubTokenValue) {
        String[] subTokenCommaSplitValues = commaPart.split(COMMA);
        if (subTokenCommaSplitValues.length != 2)
            throw new RuntimeException("2 valid parts are required in comma separated values");
        validateCommaCase(subTokenCommaSplitValues, secondSubTokenValue);

        Integer subTokenCommaSplitValueOneInteger = valueOf(subTokenCommaSplitValues[0]);
        Integer subTokenCommaSplitValueTwoInteger = valueOf(subTokenCommaSplitValues[1]);

        return IntStream.iterate(subTokenCommaSplitValueOneInteger, a -> a == subTokenCommaSplitValueOneInteger ? subTokenCommaSplitValueTwoInteger : a + secondSubTokenValue)
                .limit((long) (Math.ceil((double) (token.getMaximum() - subTokenCommaSplitValueOneInteger) / secondSubTokenValue + 1)))
                .filter(value -> value < token.getMaximum())
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }

    private void validateHyphenCase(String[] subTokenHyphenSplitValues, Integer secondSubTokenValue) {
        if (subTokenHyphenSplitValues.length != 2 || parseInt(subTokenHyphenSplitValues[0]) > parseInt(subTokenHyphenSplitValues[1])) {
            throw new RuntimeException("hyphen sub-token numerical in slash tokens should be in the format of a-b/c");
        }
        validateDerivedValueRangeWithTokenMinimumAndMaximum(valueOf(subTokenHyphenSplitValues[0]), secondSubTokenValue);
        validateDerivedValueRangeWithTokenMinimumAndMaximum(valueOf(subTokenHyphenSplitValues[1]), secondSubTokenValue);
    }

    private void validateCommaCase(String[] subTokenCommaSplitValues, Integer secondSubTokenValue) {
        if (subTokenCommaSplitValues.length != 2 || parseInt(subTokenCommaSplitValues[0]) > parseInt(subTokenCommaSplitValues[1])) {
            throw new RuntimeException("comma sub-token numerical in slash tokens should be in the format of a,b/c");
        }
        validateDerivedValueRangeWithTokenMinimumAndMaximum(valueOf(subTokenCommaSplitValues[0]), secondSubTokenValue);
        validateDerivedValueRangeWithTokenMinimumAndMaximum(valueOf(subTokenCommaSplitValues[1]), secondSubTokenValue);
    }
}
