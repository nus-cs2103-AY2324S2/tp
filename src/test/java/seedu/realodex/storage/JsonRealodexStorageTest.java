package seedu.realodex.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.realodex.testutil.Assert.assertThrows;
import static seedu.realodex.testutil.TypicalPersons.ALICE;
import static seedu.realodex.testutil.TypicalPersons.HOON;
import static seedu.realodex.testutil.TypicalPersons.IDA;
import static seedu.realodex.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.realodex.commons.exceptions.DataLoadingException;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.ReadOnlyRealodex;

public class JsonRealodexStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonRealodexStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyRealodex> readAddressBook(String filePath) throws Exception {
        return new JsonRealodexStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("notJsonFormatRealodex.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidPersonRealodex.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidAndValidPersonRealodex.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRealodex.json");
        Realodex original = getTypicalAddressBook();
        JsonRealodexStorage jsonRealodexStorage = new JsonRealodexStorage(filePath);

        // Save in new file and read back
        jsonRealodexStorage.saveAddressBook(original, filePath);
        ReadOnlyRealodex readBack = jsonRealodexStorage.readAddressBook(filePath).get();
        assertEquals(original, new Realodex(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonRealodexStorage.saveAddressBook(original, filePath);
        readBack = jsonRealodexStorage.readAddressBook(filePath).get();
        assertEquals(original, new Realodex(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonRealodexStorage.saveAddressBook(original); // file path not specified
        readBack = jsonRealodexStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new Realodex(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyRealodex addressBook, String filePath) {
        try {
            new JsonRealodexStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Realodex(), null));
    }
}
