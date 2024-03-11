package staffconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static staffconnect.testutil.Assert.assertThrows;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.HOON;
import static staffconnect.testutil.TypicalPersons.IDA;
import static staffconnect.testutil.TypicalPersons.getTypicalStaffBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.StaffBook;

public class JsonStaffBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonStaffBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readStaffBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStaffBook(null));
    }

    private java.util.Optional<ReadOnlyStaffBook> readStaffBook(String filePath) throws Exception {
        return new JsonStaffBookStorage(Paths.get(filePath)).readStaffBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readStaffBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readStaffBook("notJsonFormatStaffBook.json"));
    }

    @Test
    public void readStaffBook_invalidPersonStaffBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readStaffBook("invalidPersonStaffBook.json"));
    }

    @Test
    public void readStaffBook_invalidAndValidPersonStaffBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readStaffBook("invalidAndValidPersonStaffBook.json"));
    }

    @Test
    public void readAndSaveStaffBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStaffConnect.json");
        StaffBook original = getTypicalStaffBook();
        JsonStaffBookStorage jsonStaffBookStorage = new JsonStaffBookStorage(filePath);

        // Save in new file and read back
        jsonStaffBookStorage.saveStaffBook(original, filePath);
        ReadOnlyStaffBook readBack = jsonStaffBookStorage.readStaffBook(filePath).get();
        assertEquals(original, new StaffBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonStaffBookStorage.saveStaffBook(original, filePath);
        readBack = jsonStaffBookStorage.readStaffBook(filePath).get();
        assertEquals(original, new StaffBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonStaffBookStorage.saveStaffBook(original); // file path not specified
        readBack = jsonStaffBookStorage.readStaffBook().get(); // file path not specified
        assertEquals(original, new StaffBook(readBack));

    }

    @Test
    public void saveStaffBook_nullStaffBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStaffBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code staffBook} at the specified {@code filePath}.
     */
    private void saveStaffBook(ReadOnlyStaffBook staffBook, String filePath) {
        try {
            new JsonStaffBookStorage(Paths.get(filePath))
                    .saveStaffBook(staffBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStaffBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStaffBook(new StaffBook(), null));
    }
}
