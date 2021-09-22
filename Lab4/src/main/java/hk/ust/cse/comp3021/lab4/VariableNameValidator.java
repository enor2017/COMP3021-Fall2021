package hk.ust.cse.comp3021.lab4;

import org.jetbrains.annotations.NotNull;

/**
 * Validates variable names for Java.
 */
public class VariableNameValidator extends Validator {

    @Override
    public boolean validate(@NotNull final String input) {
        // Variable names must not be empty
        if (input.isEmpty()) {
            return false;
        }

        // Variable names cannot contain whitespace
        if (StringUtils.containsWhitespace(input)) {
            return false;
        }

        // Variable names must start with an alphabetic character
        if (!StringUtils.startWithAlphabet(input)) {
            return false;
        }

        // Variable names can contain alphanumeric characters, *as well as* underscores and dollar signs
        return StringUtils.allCharactersValid(input, true, true, "_$");
    }
}
