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
        var newPos = checkCanMove(getPlayerPos(), direction);
        if (newPos == null) {
            return new MoveResult.Invalid(getPlayerPos());
        } else {
            MoveResult result;
            // record prevPos during the process of consecutive moving
            Position prevPos = null;
            // check if the player can continue to move.
            while(true) {
                // cannot move on, since this pos is wall or out of bound
                // we just stop at previous position
                // (this cannot happen in first loop)
                if (newPos == null) {
                    result = new MoveResult.Valid.Alive(prevPos, getPlayerPos());
                    break;
                }
                // if player hit mine this step
                else if (isMine(newPos)) {
                    gameState.decrementNumLives();
                    gameState.incrementNumDeaths();
                    result = new MoveResult.Valid.Dead(getPlayerPos(), newPos);
                    break;
                }
                // or, if player reach a stop cell this step
                else if (isStop(newPos)) {
                    result = new MoveResult.Valid.Alive(newPos, getPlayerPos());
                    break;
                }
                // otherwise, player can continue moving,
                // and check if current pos is gem/extraLife
                // ATTENTION: just set the entity to null, rather than modify the values.
                else {
                    if (containsGem(newPos)) {
                        gameState.getGameBoard().getEntityCell(newPos).setEntity(null);
                    }
                    if (containsExtraLife(newPos)) {
                        gameState.getGameBoard().getEntityCell(newPos).setEntity(null);
                        gameState.increaseNumLives(1);
                    }
                    prevPos = newPos;
                    newPos = checkCanMove(newPos, direction);
                }
            }

            // for valid move, modify some parameters.
            gameState.getUndoStack().push(gameState.getGameBoard());
            gameState.incrementNumMoves();
            // we only push Valid.Alive movements into MoveStack.
            if (result instanceof MoveResult.Valid.Alive) {
                gameState.getMoveStack().push(result);
            }
            return result;
        }
    }

    /**
     * helper function: check if player can move to given direction
     * some invalid movements:
     * (1) if out of bound after movement
     * (2) if in the wall after movement
     * @param pos the current position of player
     * @param direction the direction player want to move
     * @return new position after movement, if can move; null if cannot
     */
    private Position checkCanMove(@NotNull final Position pos, @NotNull final Direction direction) {
        var offSet = direction.getOffset();
        int rows = gameState.getGameBoard().getNumRows();
        int cols = gameState.getGameBoard().getNumCols();
        var newPos = pos.offsetByOrNull(offSet, rows, cols);
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
     * check if given position is a stop cell.
     * @param pos the position to check
     * @return true if is a stop Cell, false otherwise.
     */
    private boolean isStop(@NotNull final Position pos) {
        Cell currCell = getCell(pos);
        return currCell instanceof StopCell;
    }

    /**
     * check if given position contains a gem.
     * @param pos the position to check
     * @return true if contains a gem, false otherwise.
     */
    private boolean containsGem(@NotNull final Position pos) {
        Cell currCell = getCell(pos);
        if (currCell instanceof EntityCell) {
            return ((EntityCell) currCell).getEntity() instanceof Gem;
        }
        return false;
    }

    /**
     * check if given position contains an ExtraLife entity.
     * @param pos the position to check
     * @return true if contains an ExtraLife entity, false otherwise.
     */
    private boolean containsExtraLife(@NotNull final Position pos) {
        Cell currCell = getCell(pos);
        if (currCell instanceof EntityCell) {
            return ((EntityCell) currCell).getEntity() instanceof ExtraLife;
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
        if (gameState.getMoveStack().isEmpty()) {
            return false;
        } else {
            // to undo, we pop a move from moveStack,
            // and recover gameBoard from undoStack(by calling GameBoardController)
            gameState.getMoveStack().pop();
            return true;
        }
    }
}
