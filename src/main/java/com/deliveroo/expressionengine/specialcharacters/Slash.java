package com.deliveroo.expressionengine.specialcharacters;

import com.deliveroo.expressionengine.tokens.Token;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.deliveroo.expressionengine.util.Constants.*;

public class Slash extends BaseSpecialCharacter {

    public Slash(final Token token) {
        super(token);
    }

    @Override
    public String getExplanation() {
        String[] subTokens = token.getValue().split(FORWARD_SLASH);

        validateSlashFormat(subTokens);

        Integer firstSubTokenValueOne = 0;
        Integer secondSubTokenValue = Integer.valueOf(subTokens[1]);

        if (subTokens[0].equalsIgnoreCase(ASTERISK)) {
            return handleAsteriskCase(secondSubTokenValue);
        }

        if (subTokens[0].matches(NON_NEGATIVE_NUMBERS_REGEX)) {
            firstSubTokenValueOne = Integer.valueOf(subTokens[0]);
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
        validateHyphenCase(subTokenHyphenSplitValues, secondSubTokenValue);

        Integer subTokenHyphenSplitValueOneInteger = Integer.valueOf(subTokenHyphenSplitValues[0]);
        Integer subTokenHyphenSplitValueTwoInteger = Integer.valueOf(subTokenHyphenSplitValues[1]);

        return IntStream.iterate(subTokenHyphenSplitValueOneInteger, a -> a + secondSubTokenValue)
                .limit((subTokenHyphenSplitValueTwoInteger - subTokenHyphenSplitValueOneInteger) / secondSubTokenValue + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }

    private String handleCommaCase(String commaPart, Integer secondSubTokenValue) {
        String[] subTokenCommaSplitValues = commaPart.split(COMMA);
        validateCommaCase(subTokenCommaSplitValues, secondSubTokenValue);

        Integer subTokenCommaSplitValueOneInteger = Integer.valueOf(subTokenCommaSplitValues[0]);
        Integer subTokenCommaSplitValueTwoInteger = Integer.valueOf(subTokenCommaSplitValues[1]);

        return IntStream.iterate(subTokenCommaSplitValueOneInteger, a -> a == subTokenCommaSplitValueOneInteger ? subTokenCommaSplitValueTwoInteger : a + secondSubTokenValue)
                .limit((long) (Math.ceil((double) (token.getMaximum() - subTokenCommaSplitValueOneInteger) / secondSubTokenValue + 1)))
                .filter(value -> value < token.getMaximum())
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(EMPTY_SPACE));
    }

    private void validateHyphenCase(String[] subTokenHyphenSplitValues, Integer secondSubTokenValue) {
        if (subTokenHyphenSplitValues.length != 2 || subTokenHyphenSplitValues[0].compareTo(subTokenHyphenSplitValues[1]) > 0) {
            throw new RuntimeException("hyphen sub-token in slash tokens should be in the format of a-b/c");
        }
        validateDerivedValueRangeWithTokenMinimumAndMaximum(Integer.valueOf(subTokenHyphenSplitValues[0]), secondSubTokenValue);
        validateDerivedValueRangeWithTokenMinimumAndMaximum(Integer.valueOf(subTokenHyphenSplitValues[1]), secondSubTokenValue);
    }

    private void validateCommaCase(String[] subTokenCommaSplitValues, Integer secondSubTokenValue) {
        if (subTokenCommaSplitValues.length != 2 || subTokenCommaSplitValues[0].compareTo(subTokenCommaSplitValues[1]) > 0) {
            throw new RuntimeException("comma sub-token in slash tokens should be in the format of a,b/c");
        }
        validateDerivedValueRangeWithTokenMinimumAndMaximum(Integer.valueOf(subTokenCommaSplitValues[0]), secondSubTokenValue);
        validateDerivedValueRangeWithTokenMinimumAndMaximum(Integer.valueOf(subTokenCommaSplitValues[1]), secondSubTokenValue);
    }
}
