package hk.ust.cse.comp3021.pa1.controller;

import hk.ust.cse.comp3021.pa1.model.Direction;
import hk.ust.cse.comp3021.pa1.model.GameState;
import hk.ust.cse.comp3021.pa1.model.MoveResult;
import org.jetbrains.annotations.NotNull;

/**
 * Controller for {@link hk.ust.cse.comp3021.pa1.InertiaTextGame}.
 *
 * <p>
 * All game state mutations should be performed by this class.
 * </p>
 */
public class GameController {

    @NotNull
    private final GameState gameState;

    /**
     * Creates an instance.
     *
     * @param gameState The instance of {@link GameState} to control.
     */
    public GameController(@NotNull final GameState gameState) {
        // TODO
        this.gameState = null;
    }

    /**
     * Processes a Move action performed by the player.
     *
     * @param direction The direction the player wants to move to.
     * @return An instance of {@link MoveResult} indicating the result of the action.
     */
    public MoveResult processMove(@NotNull final Direction direction) {
        // TODO
        return null;
    }

    /**
     * Processes an Undo action performed by the player.
     *
     * @return {@code false} if there are no steps to undo.
     */
    public boolean processUndo() {
        // TODO
        return false;
    }
}
