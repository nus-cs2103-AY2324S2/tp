package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalPayBack;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.PayBack;
import seedu.address.model.ReadOnlyPayBack;

public class JsonPayBackStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPayBackStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPayBack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPayBack(null));
    }

    private java.util.Optional<ReadOnlyPayBack> readPayBack(String filePath) throws Exception {
        return new JsonPayBackStorage(Paths.get(filePath)).readPayBack(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPayBack("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readPayBack("notJsonFormatPayBack.json"));
    }

    @Test
    public void readPayBack_invalidPersonPayBack_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readPayBack("invalidPersonPayBack.json"));
    }

    @Test
    public void readPayBack_invalidAndValidPersonPayBack_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readPayBack("invalidAndValidPersonPayBack.json"));
    }

    @Test
    public void readAndSavePayBack_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPayBack.json");
        PayBack original = getTypicalPayBack();
        JsonPayBackStorage jsonPayBackStorage = new JsonPayBackStorage(filePath);

        // Save in new file and read back
        jsonPayBackStorage.savePayBack(original, filePath);
        ReadOnlyPayBack readBack = jsonPayBackStorage.readPayBack(filePath).get();
        assertEquals(original, new PayBack(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonPayBackStorage.savePayBack(original, filePath);
        readBack = jsonPayBackStorage.readPayBack(filePath).get();
        assertEquals(original, new PayBack(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonPayBackStorage.savePayBack(original); // file path not specified
        readBack = jsonPayBackStorage.readPayBack().get(); // file path not specified
        assertEquals(original, new PayBack(readBack));

    }

    @Test
    public void savePayBack_nullPayBack_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePayBack(null, "SomeFile.json"));
    }

    /**
     * Saves {@code PayBack} at the specified {@code filePath}.
     */
    private void savePayBack(ReadOnlyPayBack payBack, String filePath) {
        try {
            new JsonPayBackStorage(Paths.get(filePath))
                    .savePayBack(payBack, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePayBack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePayBack(new PayBack(), null));
    }
}
