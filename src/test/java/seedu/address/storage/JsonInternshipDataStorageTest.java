package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALICE_MICROSOFT;
import static seedu.address.testutil.TypicalInternships.HOON_APPLE;
import static seedu.address.testutil.TypicalInternships.IDA_NETFLIX;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipData;
import seedu.address.model.ReadOnlyInternshipData;

public class JsonInternshipDataStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInternshipDataStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternshipData_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternshipData(null));
    }

    private java.util.Optional<ReadOnlyInternshipData> readInternshipData(String filePath) throws Exception {
        return new JsonInternshipDataStorage(Paths.get(filePath))
                .readInternshipData(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternshipData("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readInternshipData("notJsonFormatInternshipData.json"));
    }

    @Test
    public void readInternshipData_invalidInternshipInternshipData_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readInternshipData("invalidInternshipInternshipData.json"));
    }

    @Test
    public void readInternshipData_invalidAndValidInternshipInternshipData_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readInternshipData("invalidAndValidInternshipInternshipData.json"));
    }

    @Test
    public void readAndSaveInternshipData_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInternshipData.json");
        InternshipData original = getTypicalInternshipData();
        JsonInternshipDataStorage jsonInternshipDataStorage = new JsonInternshipDataStorage(filePath);

        // Save in new file and read back
        jsonInternshipDataStorage.saveInternshipData(original, filePath);
        ReadOnlyInternshipData readBack = jsonInternshipDataStorage.readInternshipData(filePath).get();
        assertEquals(original, new InternshipData(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addInternship(HOON_APPLE);
        original.removeInternship(ALICE_MICROSOFT);
        jsonInternshipDataStorage.saveInternshipData(original, filePath);
        readBack = jsonInternshipDataStorage.readInternshipData(filePath).get();
        assertEquals(original, new InternshipData(readBack));

        // Save and read without specifying file path
        original.addInternship(IDA_NETFLIX);
        jsonInternshipDataStorage.saveInternshipData(original); // file path not specified
        readBack = jsonInternshipDataStorage.readInternshipData().get(); // file path not specified
        assertEquals(original, new InternshipData(readBack));

    }

    @Test
    public void saveInternshipData_nullInternshipData_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternshipData(null, "SomeFile.json"));
    }

    /**
     * Saves {@code internshipData} at the specified {@code filePath}.
     */
    private void saveInternshipData(ReadOnlyInternshipData internshipData, String filePath) {
        try {
            new JsonInternshipDataStorage(Paths.get(filePath))
                    .saveInternshipData(internshipData, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternshipData_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternshipData(new InternshipData(), null));
    }
}
