package hk.ust.cse.comp3021.lab7;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A {@link javafx.scene.image.Image} loaded from a path under {@code resources}.
 */
public class ResourceImage {

    /**
     * The path under {@code resources} to load the image from.
     */
    @NotNull
    private final String resPath;
    /**
     * The cached instance of {@link javafx.scene.image.Image}, if the image has already been loaded.
     */
    private Image cachedImage = null;

    /**
     * Creates a new instance which loads from the specified path.
     *
     * @param resPath The path of the resource to load from.
     */
    ResourceImage(@NotNull final String resPath) {
        this.resPath = resPath;
    }

    /**
     * Returns an instance of {@link javafx.scene.image.Image} of the loaded image.
     *
     * <p>
     * If the image has not been loaded yet, it will also be loaded at this point.
     * </p>
     *
     * @return An instance of {@link javafx.scene.image.Image} representing the loaded image.
     */
    @NotNull
    synchronized Image get() {
        if (cachedImage != null) {
            return cachedImage;
        }

        final var resourceUrl = Objects.requireNonNull(getClass().getResource(resPath));
        cachedImage = new Image(resourceUrl.toExternalForm());
        return cachedImage;
    }
}
