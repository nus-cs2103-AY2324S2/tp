package scrolls.elder.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.ObservableList;
import scrolls.elder.commons.core.index.Index;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of an ObservableList.
     */
    public static Index getMidIndex(ObservableList<?> list) {
        return Index.fromOneBased(list.size() / 2);
    }

    /**
     * Returns the last index of an ObservableList.
     */
    public static Index getLastIndex(ObservableList<?> list) {
        return Index.fromOneBased(list.size());
    }
}
