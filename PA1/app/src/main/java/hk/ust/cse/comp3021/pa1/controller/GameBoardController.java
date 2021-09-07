package hk.ust.cse.comp3021.pa1.controller;

import hk.ust.cse.comp3021.pa1.model.Direction;
import hk.ust.cse.comp3021.pa1.model.GameBoard;
import hk.ust.cse.comp3021.pa1.model.MoveResult;
import org.jetbrains.annotations.NotNull;

/**
 * Controller for {@link GameBoard}.
 *
 * <p>
 * This class is responsible for providing high-level operations to mutate a {@link GameBoard}. This should be the only
 * class which mutates the game board; Other classes should use this class to mutate the game board.
 * </p>
 */
public class GameBoardController {

    @NotNull
    private final GameBoard gameBoard;

    /**
     * Creates an instance.
     *
     * @param gameBoard The instance of {@link GameBoard} to control.
     */
    public GameBoardController(@NotNull final GameBoard gameBoard) {
        // TODO
        this.gameBoard = null;
    }

    /**
     * Moves the player in the given direction.
     *
     * <p>
     * You should ensure that the game board is only mutated if the move is valid and results in the player still being
     * alive. If the player dies after moving or the move is invalid, the game board should remain in the same state as
     * before this method was called.
     * </p>
     *
     * @param direction Direction to move the player in.
     * @return An instance of {@link MoveResult} representing the result of this action.
     */
    @NotNull
    public MoveResult makeMove(@NotNull final Direction direction) {
        // TODO
        return null;
    }

    /**
     * Undoes a move by reverting all changes performed by the specified move.
     *
     * <p>
     * Hint: Undoing a move is effectively the same as reversing everything you have done to make a move.
     * </p>
     *
     * @param prevMove The {@link MoveResult} object to revert.
     */
    public void undoMove(@NotNull final MoveResult prevMove) {
        // TODO
    }
}
