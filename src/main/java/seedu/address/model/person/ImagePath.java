package seedu.address.model.person;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ImagePath {
    private Path filePath = DEFAULT;

    private static Path DEFAULT = Paths.get("/images/default_image.png");

    public ImagePath() {
        this.filePath = DEFAULT;
    }

    public ImagePath(Path path) {
        this.filePath = path;
    }

    public Path getFilePath() {
        return this.filePath;
    }
}
