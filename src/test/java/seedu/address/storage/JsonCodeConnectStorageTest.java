package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.HOON;
import static seedu.address.testutil.TypicalContacts.IDA;
import static seedu.address.testutil.TypicalContacts.getTypicalCodeConnect;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.CodeConnect;
import seedu.address.model.ReadOnlyCodeConnect;

public class JsonCodeConnectStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCodeConnectStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCodeConnect_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCodeConnect(null));
    }

    private java.util.Optional<ReadOnlyCodeConnect> readCodeConnect(String filePath) throws Exception {
        return new JsonCodeConnectStorage(Paths.get(filePath)).readCodeConnect(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCodeConnect("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readCodeConnect("notJsonFormatCodeConnect.json"));
    }

    @Test
    public void readCodeConnect_invalidContactCodeConnect_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCodeConnect("invalidContactCodeConnect.json"));
    }

    @Test
    public void readCodeConnect_invalidAndValidContactCodeConnect_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCodeConnect("invalidAndValidContactCodeConnect.json"));
    }

    @Test
    public void readAndSaveCodeConnect_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        CodeConnect original = getTypicalCodeConnect();
        JsonCodeConnectStorage jsonCodeConnectStorage = new JsonCodeConnectStorage(filePath);

        // Save in new file and read back
        jsonCodeConnectStorage.saveCodeConnect(original, filePath);
        ReadOnlyCodeConnect readBack = jsonCodeConnectStorage.readCodeConnect(filePath).get();
        assertEquals(original, new CodeConnect(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(HOON);
        original.removeContact(ALICE);
        jsonCodeConnectStorage.saveCodeConnect(original, filePath);
        readBack = jsonCodeConnectStorage.readCodeConnect(filePath).get();
        assertEquals(original, new CodeConnect(readBack));

        // Save and read without specifying file path
        original.addContact(IDA);
        jsonCodeConnectStorage.saveCodeConnect(original); // file path not specified
        readBack = jsonCodeConnectStorage.readCodeConnect().get(); // file path not specified
        assertEquals(original, new CodeConnect(readBack));

    }

    @Test
    public void saveCodeConnect_nullCodeConnect_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCodeConnect(null, "SomeFile.json"));
    }

    /**
     * Saves {@code codeConnect} at the specified {@code filePath}.
     */
    private void saveCodeConnect(ReadOnlyCodeConnect codeConnect, String filePath) {
        try {
            new JsonCodeConnectStorage(Paths.get(filePath))
                    .saveCodeConnect(codeConnect, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCodeConnect_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCodeConnect(new CodeConnect(), null));
    }
}
