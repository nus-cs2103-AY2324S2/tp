package seedu.address.model.person;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Image {
    private Path filePath;

    private static Path DEFAULT = Paths.get("/images/default_image.png");

    public Image() {
        this.filePath = DEFAULT;
    }
    public Image(Path path) {
        this.filePath = path;
    }
}
