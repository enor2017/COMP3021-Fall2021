package hk.ust.cse.comp3021.pa1.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link java.util.Stack}-like data structure to track all valid moves made by a player.
 *
 * <p>A stack is a data structure which enforces Last-In First-Out (LIFO) ordering of its elements.</p>
 *
 * <p>You can read more about stacks <a href="https://en.wikipedia.org/wiki/Stack_(abstract_data_type)">here</a>.</p>
 */
public class MoveStack {

    @NotNull
    private final List<MoveResult> moves = new ArrayList<>();

    private int popCount = 0;

    /**
     * Pushes a move to this stack.
     *
     * <p>
     * In our testing, we will only push instances of {@link MoveResult.Valid.Alive}; How you handle other
     * sealed-subclasses of {@link MoveResult} is entirely up to you.
     * </p>
     *
     * @param move The move to push into this stack.
     */
    public void push(@NotNull final MoveResult move) {
        // TODO
    }

    /**
     * @return Whether the stack is currently empty.
     */
    public boolean isEmpty() {
        // TODO
        return false;
    }

    /**
     * Pops a move from this stack.
     *
     * @return The instance of {@link MoveResult} last performed by the player.
     */
    @NotNull
    public MoveResult pop() {
        // TODO
        return null;
    }

    /**
     * @return The number of {@link MoveStack#pop} calls invoked.
     */
    public int getPopCount() {
        // TODO
        return 0;
    }

    /**
     * Peeks the topmost of the element of the stack.
     *
     * <p>
     * This method is intended for internal use only, although we will not stop you if you want to use this method in
     * your implementation.
     * </p>
     *
     * @return The instance of {@link MoveResult} at the top of the stack, corresponding to the last move performed by
     * the player.
     */
    @NotNull
    public MoveResult peek() {
        // TODO
        return null;
    }
}
