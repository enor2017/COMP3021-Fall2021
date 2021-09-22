package hk.ust.cse.comp3021.lab4;

import org.jetbrains.annotations.NotNull;

/**
 * <p>Referenced from <a href="https://en.wikipedia.org/wiki/Email_address">here</a>.</p>
 */
public class EmailValidator extends Validator {

    @Override
    public boolean validate(@NotNull final String input) {
        // Email addresses should have two components when split by '@'
        final var split = StringUtils.splitByCharacter(input, '@');
        if (split.size() != 2) {
            return false;
        }

        final var local = split.get(0);
        final var domain = split.get(1);

        // Local component must be shorter than 64 characters
        // Domain component must be shorter than 256 characters
        if (!StringUtils.lengthLessThanEqual(local, 63) || !StringUtils.lengthLessThanEqual(domain, 255)) {
            return false;
        }

        // All characters in the local component must be alphanumeric or within the given set of valid characters
        if (!StringUtils.allCharactersValid(local, true, true, "!#$%&'*+-/=?^_`{|}~.")) {
            return false;
        }

        // There must be no consecutive dots, nor should there be a leading or trailing dot in the local component
        final var localSplitByDot = StringUtils.splitByCharacter(local, '.');
        if (localSplitByDot.stream().anyMatch(String::isEmpty)) {
            return false;
        }

        // All characters in the domain component must be alphanumeric or within the given set of valid characters
        return StringUtils.allCharactersValid(domain, true, true, "-.");
    }
}
