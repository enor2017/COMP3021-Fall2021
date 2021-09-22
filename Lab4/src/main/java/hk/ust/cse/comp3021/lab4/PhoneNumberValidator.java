package hk.ust.cse.comp3021.lab4;

import org.jetbrains.annotations.NotNull;

/**
 * Validates a phone number with E.164 format.
 */
public class PhoneNumberValidator extends Validator {

    @Override
    public boolean validate(@NotNull final String input) {
        // Phone numbers must not be empty
        if (input.isEmpty()) {
            return false;
        }

        final var startsWithPlusPrefix = input.codePointAt(0) == '+';

        // Phone numbers must either start with a digit or '+'
        if (!Character.isDigit(StringUtils.getCharAt(input, 0)) && !startsWithPlusPrefix) {
            return false;
        }

        // Phone numbers which start with '+' can have up to 15 digits (excluding the '+' prefix)
        if (startsWithPlusPrefix && !StringUtils.lengthLessThanEqual(input, 16)) {
            return false;
        }
        // Phone numbers which do not start with '+' can have up to 12 digits
        if (!startsWithPlusPrefix && !StringUtils.lengthLessThanEqual(input, 12)) {
            return false;
        }

        final String numericPart;
        if (startsWithPlusPrefix) {
            numericPart = input.substring(1);
        } else {
            numericPart = input;
        }

        // Phone numbers should only contain digits (except for the '+' prefix)
        return StringUtils.allCharactersValid(numericPart, false, true, "");
    }
}
