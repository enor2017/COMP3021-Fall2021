package hk.ust.cse.comp3021.pa1.model;

import org.jetbrains.annotations.NotNull;

/**
 * An extra life entity on a game board.
 *
 * <p>
 * An extra life entity gives the player an extra life when picked up.
 * </p>
 */
public final class ExtraLife extends Entity {

    /**
     * Creates an instance of {@link ExtraLife}, initially not present on any {@link EntityCell}.
     */
    public ExtraLife() {
        // TODO
    }

    /**
     * Creates an instance of {@link ExtraLife}.
     *
     * @param owner The initial {@link EntityCell} the extra life belongs to.
     */
    public ExtraLife(@NotNull final EntityCell owner) {
        // TODO
    }

    @Override
    public char toUnicodeChar() {
        return '\u2661';
    }

    @Override
    public char toASCIIChar() {
        return 'L';
    }
}
