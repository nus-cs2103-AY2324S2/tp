package vitalconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static vitalconnect.testutil.Assert.assertThrows;
import static vitalconnect.testutil.TypicalPersons.ALICE;
import static vitalconnect.testutil.TypicalPersons.HOON;
import static vitalconnect.testutil.TypicalPersons.IDA;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import vitalconnect.commons.exceptions.DataLoadingException;
import vitalconnect.model.Clinic;
import vitalconnect.model.ReadOnlyClinic;

public class JsonClinicStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonClinicStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClinic_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClinic(null));
    }

    private java.util.Optional<ReadOnlyClinic> readClinic(String filePath) throws Exception {
        return new JsonClinicStorage(Paths.get(filePath)).readClinic(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClinic("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readClinic("notJsonFormatClinic.json"));
    }

    @Test
    public void readClinic_invalidPersonClinic_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readClinic("invalidPersonClinic.json"));
    }

    @Test
    public void readClinic_invalidAndValidPersonClinic_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readClinic("invalidAndValidPersonClinic.json"));
    }

    @Test
    public void readAndSaveClinic_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClinic.json");
        Clinic original = getTypicalClinic();
        JsonClinicStorage jsonClinicStorage = new JsonClinicStorage(filePath);

        // Save in new file and read back
        jsonClinicStorage.saveClinic(original, filePath);
        ReadOnlyClinic readBack = jsonClinicStorage.readClinic(filePath).get();
        assertEquals(original, new Clinic(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonClinicStorage.saveClinic(original, filePath);
        readBack = jsonClinicStorage.readClinic(filePath).get();
        assertEquals(original, new Clinic(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonClinicStorage.saveClinic(original); // file path not specified
        readBack = jsonClinicStorage.readClinic().get(); // file path not specified
        assertEquals(original, new Clinic(readBack));

    }

    @Test
    public void saveClinic_nullClinic_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClinic(null, "SomeFile.json"));
    }

    /**
     * Saves {@code clinic} at the specified {@code filePath}.
     */
    private void saveClinic(ReadOnlyClinic clinic, String filePath) {
        try {
            new JsonClinicStorage(Paths.get(filePath))
                    .saveClinic(clinic, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClinic_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClinic(new Clinic(), null));
    }
}
