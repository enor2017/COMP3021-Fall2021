package hk.ust.cse.comp3021.pa1.model;

import org.jetbrains.annotations.NotNull;

/**
 * The main game board of the game.
 *
 * <p>
 * The top-left hand corner of the game board is the "origin" of the board (0, 0).
 * </p>
 */
public final class GameBoard {

    /**
     * Number of rows in the game board.
     */
    private final int numRows;
    /**
     * Number of columns in the game board.
     */
    private final int numCols;

    /**
     * 2D array representing each cell in the game board.
     */
    @NotNull
    private final Cell[][] board;

    /**
     * The instance of {@link Player} on this game board.
     */
    @NotNull
    private final Player player;

    /**
     * Creates an instance using the provided creation parameters.
     *
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     * @param cells   The initial values of cells.
     * @throws IllegalArgumentException if any of the following are true:
     *                                  <ul>
     *                                      <li>{@code numRows} is not equal to {@code cells.length}</li>
     *                                      <li>{@code numCols} is not equal to {@code cells[0].length}</li>
     *                                      <li>There is no player or more than one player in {@code cells}</li>
     *                                      <li>There are no gems in {@code cells}</li>
     *                                      <li>There are some gems which cannot be reached by the player</li>
     *                                  </ul>
     */
    public GameBoard(final int numRows, final int numCols, @NotNull final Cell[][] cells) {
        // TODO
        this.numRows = 0;
        this.numCols = 0;
        this.board = null;
        this.player = null;
    }

    /**
     * Returns the {@link Cell}s of a single row of the game board.
     *
     * @param r Row index.
     * @return 1D array representing the row. The first element in the array corresponds to the leftmost element of the
     * row.
     */
    @NotNull
    public Cell[] getRow(final int r) {
        // TODO
        return null;
    }

    /**
     * Returns the {@link Cell}s of a single column of the game board.
     *
     * @param c Column index.
     * @return 1D array representing the column. The first element in the array corresponds to the topmost element of
     * the row.
     */
    @NotNull
    public Cell[] getCol(final int c) {
        // TODO
        return null;
    }

    /**
     * Returns a single cell of the game board.
     *
     * @param r Row index.
     * @param c Column index.
     * @return The {@link Cell} instance at the specified location.
     */
    @NotNull
    public Cell getCell(final int r, final int c) {
        // TODO
        return null;
    }

    /**
     * Returns a single cell of the game board.
     *
     * @param position The position object representing the location of the cell.
     * @return The {@link Cell} instance at the specified location.
     */
    @NotNull
    public Cell getCell(@NotNull final Position position) {
        // TODO
        return null;
    }

    /**
     * Returns an {@link EntityCell} on the game board.
     *
     * <p>
     * This method is a convenience method for getting a cell which is unconditionally known to be an entity cell.
     * </p>
     *
     * @param r Row index.
     * @param c Column index.
     * @return The {@link EntityCell} instance at the specified location.
     * @throws IllegalArgumentException if the cell at the specified position is not an instance of {@link EntityCell}.
     */
    @NotNull
    public EntityCell getEntityCell(final int r, final int c) {
        // TODO
        return null;
    }

    /**
     * Returns an {@link EntityCell} on the game board.
     *
     * <p>
     * This method is a convenience method for getting a cell which is unconditionally known to be an entity cell.
     * </p>
     *
     * @param position The position object representing the location of the cell.
     * @return The {@link EntityCell} instance at the specified location.
     * @throws IllegalArgumentException if the cell at the specified position is not an instance of {@link EntityCell}.
     */
    @NotNull
    public EntityCell getEntityCell(@NotNull final Position position) {
        // TODO
        return null;
    }

    /**
     * @return The number of rows of this game board.
     */
    public int getNumRows() {
        // TODO
        return 0;
    }

    /**
     * @return The number of columns of this game board.
     */
    public int getNumCols() {
        // TODO
        return 0;
    }

    /**
     * @return The player instance.
     */
    @NotNull
    public Player getPlayer() {
        // TODO
        return null;
    }

    /**
     * @return The number of gems still present in the game board.
     */
    public int getNumGems() {
        // TODO
        return 0;
    }
}
