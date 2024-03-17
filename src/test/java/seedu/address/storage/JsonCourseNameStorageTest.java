package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.CourseName;
import seedu.address.model.ReadOnlyCourseName;

public class JsonCourseNameStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCourseNameStorageTest");
    @TempDir
    public Path testFolder;

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    private java.util.Optional<ReadOnlyCourseName> readCourse(String filePath) throws Exception {
        return new JsonCourseNameStorage(Paths.get(filePath)).readCourse(addToTestDataPathIfNotNull(filePath));
    }

    @Test
    public void readCourseName_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCourse(null));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCourse("NonExistentFile.json").isPresent());
    }

    @Test
    public void saveCourseName_nullCourseName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCourse(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveCourse(ReadOnlyCourseName courseName, String filePath) {
        try {
            new JsonCourseNameStorage(Paths.get(filePath))
                    .saveCourse(courseName, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCourseName_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCourse(new CourseName(), null));
    }

}


