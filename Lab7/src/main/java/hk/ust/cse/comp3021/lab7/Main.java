package hk.ust.cse.comp3021.lab7;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    /**
     * The default number of rows if no command-line arguments are provided.
     */
    private static final int DEFAULT_NUM_ROWS = 5;
    /**
     * The default number of columns if no command-line arguments are provided.
     */
    private static final int DEFAULT_NUM_COLS = 5;

    /**
     * The default size of each cell, for both width and height.
     */
    private static final int CELL_SIZE = 32;

    /**
     * An {@link javafx.scene.image.Image} of a blank cell.
     */
    private static final Image BLANK_CELL_IMAGE = new ResourceImage("/images/blank.png").get();
    /**
     * An {@link javafx.scene.image.Image} of a wall cell.
     */
    private static final Image WALL_CELL_IMAGE = new ResourceImage("/images/wall.png").get();

    /**
     * Parses the argument list to compute the number of rows to generate.
     *
     * @return The number of rows of the game board.
     */
    private int getNumRowsFromArgs() {
        final var params = getParameters().getRaw();

        return switch (params.size()) {
            case 1, 2 -> Integer.parseInt(params.get(0));
            case 0 -> DEFAULT_NUM_ROWS;
            default -> throw new IllegalArgumentException("Invalid number of arguments");
        };
    }

    /**
     * Parses the argument list to compute the number of columns to generate.
     *
     * @return The number of columns of the game board.
     */
    private int getNumColsFromArgs() {
        final var params = getParameters().getRaw();

        return switch (params.size()) {
            case 2 -> Integer.parseInt(params.get(1));
            case 1 -> Integer.parseInt(params.get(0));
            case 0 -> DEFAULT_NUM_COLS;
            default -> throw new IllegalArgumentException("Invalid number of arguments");
        };
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * You should do the following in this method:
     * </p>
     * <ul>
     *     <li>Set the title of the window to "Lab 7"</li>
     *     <li>Set the scene of the primary stage to the scene created by {@link Main#createBoardScene(int, int)}</li>
     *     <li>Show the primary stage</li>
     * </ul>
     * <p>
     * Hint: For the parameters to {@link Main#createBoardScene(int, int)}, you may use
     * {@link Main#getNumRowsFromArgs()} and {@link Main#getNumColsFromArgs()} to get the number of rows and columns you
     * need to display.
     * </p>
     */
    @Override
    public void start(@NotNull final Stage primaryStage) {
        // TODO
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Creates a scene containing the generated game board.
     *
     * @param numRows Number of rows to generate.
     * @param numCols Number of columns to generate.
     * @return A {@link javafx.scene.Scene} containing the generated game board.
     */
    @NotNull
    private static Scene createBoardScene(final int numRows, final int numCols) {
        // TODO
    }

    /**
     * Creates a {@link javafx.scene.layout.GridPane} representing the game board.
     *
     * <p>
     * The generated game board should be in a checkerboard pattern, in other words:
     * </p>
     * <pre>
     * xoxoxo
     * oxoxox
     * xoxoxo
     * oxoxox
     * </pre>
     * <p>
     * Note: The top-left corner should always be a blank cell.
     * </p>
     * <p>
     * Hint: You may use {@link GridPane#add(Node, int, int)} to add child nodes to a {@code GridPane}.
     * </p>
     *
     * @param numRows Number of rows to generate.
     * @param numCols Number of columns to generate.
     * @return A {@link javafx.scene.layout.GridPane} containing the game board.
     */
    @NotNull
    private static GridPane createGridPane(final int numRows, final int numCols) {
        // TODO
    }

    /**
     * Creates an {@link javafx.scene.image.ImageView} from a {@link javafx.scene.image.Image}.
     *
     * <p>
     * You should set the width and height of the image view to {@link Main#CELL_SIZE} as well.
     * </p>
     *
     * @param image {@code Image} to create an {@code ImageView} from.
     * @return An {@link javafx.scene.image.ImageView} of the input image.
     */
    @NotNull
    private static ImageView createImageView(@NotNull final Image image) {
        // TODO
    }
}
