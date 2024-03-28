package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalPatientList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.PatientList;
import seedu.address.model.ReadOnlyPatientList;

public class JsonPatientListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPatientListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPatientList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPatientList(null));
    }

    private java.util.Optional<ReadOnlyPatientList> readPatientList(String filePath) throws Exception {
        return new JsonPatientListStorage(Paths.get(filePath)).readPatientList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPatientList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readPatientList("notJsonFormatPatientList.json"));
    }

    @Test
    public void readPatientList_invalidPersonPatientList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readPatientList("invalidPersonPatientList.json"));
    }

    @Test
    public void readPatientList_invalidAndValidPersonPatientList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readPatientList("invalidAndValidPersonPatientList.json"));
    }

    @Test
    public void readAndSavePatientList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPatientList.json");
        PatientList original = getTypicalPatientList();
        JsonPatientListStorage jsonPatientListStorage = new JsonPatientListStorage(filePath);

        // Save in new file and read back
        jsonPatientListStorage.savePatientList(original, filePath);
        ReadOnlyPatientList readBack = jsonPatientListStorage.readPatientList(filePath).get();
        assertEquals(original, new PatientList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonPatientListStorage.savePatientList(original, filePath);
        readBack = jsonPatientListStorage.readPatientList(filePath).get();
        assertEquals(original, new PatientList(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonPatientListStorage.savePatientList(original); // file path not specified
        readBack = jsonPatientListStorage.readPatientList().get(); // file path not specified
        assertEquals(original, new PatientList(readBack));

    }

    @Test
    public void savePatientList_nullPatientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePatientList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code patientList} at the specified {@code filePath}.
     */
    private void savePatientList(ReadOnlyPatientList patientList, String filePath) {
        try {
            new JsonPatientListStorage(Paths.get(filePath))
                    .savePatientList(patientList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePatientList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePatientList(new PatientList(), null));
    }

}
