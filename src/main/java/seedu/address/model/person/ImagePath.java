package seedu.address.model.person;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Represents the file path of an image.
 */
public class ImagePath {

    private static final Path DEFAULT_IMAGE_PATH = Paths.get("/images/default_image_2.png");
    private Path filePath = DEFAULT_IMAGE_PATH;
    /**
     * Constructs an ImagePath object with the default file path.
     */
    public ImagePath() {
        this.filePath = DEFAULT_IMAGE_PATH;
    }


    /**
     * Constructs an ImagePath object with the specified file path.
     *
     * @param path The file path of the image.
     */
    public ImagePath(Path path) {
        this.filePath = path;
    }

    /**
     * Gets the file path of the image.
     *
     * @return The file path of the image.
     */
    public Path getFilePath() {
        return this.filePath;
    }
}
