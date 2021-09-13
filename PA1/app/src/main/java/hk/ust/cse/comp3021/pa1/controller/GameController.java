package hk.ust.cse.comp3021.pa1.controller;

import hk.ust.cse.comp3021.pa1.model.*;
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
        this.gameState = gameState;
    }

    /**
     * Processes a Move action performed by the player.
     *
     * @param direction The direction the player wants to move to.
     * @return An instance of {@link MoveResult} indicating the result of the action.
     */
    public MoveResult processMove(@NotNull final Direction direction) {
        var newPos = checkCanMove(direction);
        if (newPos == null) {
            return new MoveResult.Invalid(getPlayerPos());
        } else {
            MoveResult result;
            if (isMine(newPos)) {
                result = new MoveResult.Valid.Dead(getPlayerPos(), newPos);
            } else {
                result = new MoveResult.Valid.Alive(newPos, getPlayerPos());
            }
            // for valid move, modify some parameters.
            gameState.incrementNumMoves();
            gameState.getMoveStack().push(result);
            return result;
        }
    }

    /**
     * helper function: check if player can move to given direction
     * some invalid movements:
     * (1) if out of bound after movement
     * (2) if in the wall after movement
     * @param direction the direction player want to move
     * @return new position after movement, if can move; null if cannot
     */
    private Position checkCanMove(@NotNull final Direction direction) {
        var offSet = direction.getOffset();
        var playerPos = getPlayerPos();
        int rows = gameState.getGameBoard().getNumRows();
        int cols = gameState.getGameBoard().getNumCols();
        var newPos = playerPos.offsetByOrNull(offSet, rows, cols);
        // if out of bound or is a wall cell
        if (newPos == null || getCell(newPos) instanceof Wall) {
            return null;
        }
        return newPos;
    }

    /**
     * check if given position is a mine cell.
     * @param pos the position to check
     * @return true if is a mine, false otherwise.
     */
    private boolean isMine(@NotNull final Position pos) {
        Cell currCell = getCell(pos);
        if (currCell instanceof EntityCell) {
            return ((EntityCell) currCell).getEntity() instanceof Mine;
        }
        return false;
    }

    /**
     * helper method: get the cell object at given position
     * @param pos given position
     * @return the cell at that position.
     */
    private Cell getCell(@NotNull final Position pos) {
        return gameState.getGameBoard().getCell(pos);
    }

    /**
     * helper method: return the player's position
     * @return the player's position, in a 'Position' object.
     */
    private Position getPlayerPos() {
        return gameState.getGameBoard().getPlayerPos();
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
