package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_UNINCLUDED_GROUP;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.GroupList;
import seedu.address.model.ReadOnlyGroupList;

public class JsonGroupListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonGroupListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGroupList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGroupList(null));
    }

    private java.util.Optional<ReadOnlyGroupList> readGroupList(String filePath) throws Exception {
        return new JsonGroupListStorage(Paths.get(filePath)).readGroupList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGroupList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readGroupList("notJsonFormatGroupList.json"));
    }

    @Test
    public void readGroupList_invalidGroupGroupList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readGroupList("invalidGroupGroupList.json"));
    }

    @Test
    public void readGroupList_invalidAndValidGroupGroupList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readGroupList("invalidAndValidGroupGroupList.json"));
    }

    @Test
    public void readAndSaveGroupList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGroupList.json");
        GroupList original = getTypicalGroupList();
        JsonGroupListStorage jsonGroupListStorage = new JsonGroupListStorage(filePath);

        // Save in new file and read back
        jsonGroupListStorage.saveGroupList(original, filePath);
        ReadOnlyGroupList readBack = jsonGroupListStorage.readGroupList(filePath).get();
        assertEquals(original, new GroupList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGroup(SAMPLE_UNINCLUDED_GROUP);
        original.removeGroup(SAMPLE_GROUP_1);
        jsonGroupListStorage.saveGroupList(original, filePath);
        readBack = jsonGroupListStorage.readGroupList(filePath).get();
        assertEquals(original, new GroupList(readBack));

        // Save and read without specifying file path
        original.addGroup(SAMPLE_GROUP_1);
        jsonGroupListStorage.saveGroupList(original); // file path not specified
        readBack = jsonGroupListStorage.readGroupList().get(); // file path not specified
        assertEquals(original, new GroupList(readBack));

    }

    @Test
    public void saveGroupList_nullGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGroupList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code GroupList} at the specified {@code filePath}.
     */
    private void saveGroupList(ReadOnlyGroupList groupList, String filePath) {
        try {
            new JsonGroupListStorage(Paths.get(filePath))
                    .saveGroupList(groupList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGroupList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGroupList(new GroupList(), null));
    }
}
