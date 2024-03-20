package seedu.realodex.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.realodex.testutil.Assert.assertThrows;
import static seedu.realodex.testutil.TypicalPersons.ALICE;
import static seedu.realodex.testutil.TypicalPersons.HOON;
import static seedu.realodex.testutil.TypicalPersons.IDA;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.realodex.commons.exceptions.DataLoadingException;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.Realodex;

public class JsonRealodexStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonRealodexStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRealodex_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRealodex(null));
    }

    private java.util.Optional<ReadOnlyRealodex> readRealodex(String filePath) throws Exception {
        return new JsonRealodexStorage(Paths.get(filePath)).readRealodex(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRealodex("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readRealodex("notJsonFormatRealodex.json"));
    }

    @Test
    public void readRealodex_invalidPersonRealodex_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readRealodex("invalidPersonRealodex.json"));
    }

    @Test
    public void readRealodex_invalidAndValidPersonRealodex_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readRealodex("invalidAndValidPersonRealodex.json"));
    }

    @Test
    public void readAndSaveRealodex_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRealodex.json");
        Realodex original = getTypicalRealodex();
        JsonRealodexStorage jsonRealodexStorage = new JsonRealodexStorage(filePath);

        // Save in new file and read back
        jsonRealodexStorage.saveRealodex(original, filePath);
        ReadOnlyRealodex readBack = jsonRealodexStorage.readRealodex(filePath).get();
        assertEquals(original, new Realodex(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonRealodexStorage.saveRealodex(original, filePath);
        readBack = jsonRealodexStorage.readRealodex(filePath).get();
        assertEquals(original, new Realodex(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonRealodexStorage.saveRealodex(original); // file path not specified
        readBack = jsonRealodexStorage.readRealodex().get(); // file path not specified
        assertEquals(original, new Realodex(readBack));

    }

    @Test
    public void saveRealodex_nullRealodex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRealodex(null, "SomeFile.json"));
    }

    /**
     * Saves {@code realodex} at the specified {@code filePath}.
     */
    private void saveRealodex(ReadOnlyRealodex realodex, String filePath) {
        try {
            new JsonRealodexStorage(Paths.get(filePath))
                    .saveRealodex(realodex, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRealodex_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRealodex(new Realodex(), null));
    }
}
