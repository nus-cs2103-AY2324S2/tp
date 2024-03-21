package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalNetConnect;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.NetConnect;
import seedu.address.model.ReadOnlyNetConnect;

public class JsonNetConnectStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNetConnectStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNetConnect_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNetConnect(null));
    }

    private java.util.Optional<ReadOnlyNetConnect> readNetConnect(String filePath) throws Exception {
        return new JsonNetConnectStorage(Paths.get(filePath)).readNetConnect(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNetConnect("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readNetConnect("notJsonFormatNetConnect.json"));
    }

    @Test
    public void readNetConnect_invalidPersonNetConnect_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readNetConnect("invalidPersonNetConnect.json"));
    }

    @Test
    public void readNetConnect_invalidAndValidPersonNetConnect_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readNetConnect("invalidAndValidPersonNetConnect.json"));
    }

    @Test
    public void readAndSaveNetConnect_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNetConnect.json");
        NetConnect original = getTypicalNetConnect();
        JsonNetConnectStorage jsonNetConnectStorage = new JsonNetConnectStorage(filePath);

        // Save in new file and read back
        jsonNetConnectStorage.saveNetConnect(original, filePath);
        ReadOnlyNetConnect readBack = jsonNetConnectStorage.readNetConnect(filePath).get();
        assertEquals(original, new NetConnect(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonNetConnectStorage.saveNetConnect(original, filePath);
        readBack = jsonNetConnectStorage.readNetConnect(filePath).get();
        assertEquals(original, new NetConnect(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonNetConnectStorage.saveNetConnect(original); // file path not specified
        readBack = jsonNetConnectStorage.readNetConnect().get(); // file path not specified
        assertEquals(original, new NetConnect(readBack));

    }

    @Test
    public void saveNetConnect_nullNetConnect_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNetConnect(null, "SomeFile.json"));
    }

    /**
     * Saves {@code netConnect} at the specified {@code filePath}.
     */
    private void saveNetConnect(ReadOnlyNetConnect netConnect, String filePath) {
        try {
            new JsonNetConnectStorage(Paths.get(filePath))
                    .saveNetConnect(netConnect, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNetConnect_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNetConnect(new NetConnect(), null));
    }
}
