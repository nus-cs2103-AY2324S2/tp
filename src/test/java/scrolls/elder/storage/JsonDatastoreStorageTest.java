package scrolls.elder.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import scrolls.elder.commons.exceptions.DataLoadingException;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.PersonStore;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.person.Person;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.TypicalDatastore;
import scrolls.elder.testutil.TypicalPersons;

public class JsonDatastoreStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDatastoreStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDatastore_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readDatastore(null));
    }

    private Optional<ReadOnlyDatastore> readDatastore(String filePath) throws Exception {
        return new JsonDatastoreStorage(Paths.get(filePath)).readDatastore(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDatastore("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataLoadingException.class, () -> readDatastore("notJsonFormatDatastore.json"));
    }

    @Test
    public void readDatastore_invalidPersonDatastore_throwDataLoadingException() {
        Assert.assertThrows(DataLoadingException.class, () -> readDatastore("invalidPersonDatastore.json"));
    }

    @Test
    public void readDatastore_invalidAndValidPersonDatastore_throwDataLoadingException() {
        Assert.assertThrows(DataLoadingException.class, () -> readDatastore("invalidAndValidPersonDatastore.json"));
    }

    @Test
    public void readAndSaveDatastore_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDatastore.json");
        Datastore original = TypicalDatastore.getTypicalDatastore();
        PersonStore personStore = original.getMutablePersonStore();
        JsonDatastoreStorage jsonDatastoreStorage = new JsonDatastoreStorage(filePath);

        // Save in new file and read back
        jsonDatastoreStorage.saveDatastore(original, filePath);
        ReadOnlyDatastore readBack = jsonDatastoreStorage.readDatastore(filePath).get();
        assertEquals(original, new Datastore(readBack));

        // Modify data, overwrite exiting file, and read back
        Person hoon = TypicalPersons.HOON;
        personStore.addPerson(hoon);
        personStore.removePerson(TypicalPersons.ALICE);
        jsonDatastoreStorage.saveDatastore(original, filePath);
        readBack = jsonDatastoreStorage.readDatastore(filePath).get();
        assertEquals(original, new Datastore(readBack));

        // Save and read without specifying file path
        Person ida = TypicalPersons.IDA;
        personStore.addPerson(ida);
        jsonDatastoreStorage.saveDatastore(original); // file path not specified
        readBack = jsonDatastoreStorage.readDatastore().get(); // file path not specified
        assertEquals(original, new Datastore(readBack));
    }

    @Test
    public void saveDatastore_nullDatastore_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveDatastore(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Datastore} at the specified {@code filePath}.
     */
    private void saveDatastore(ReadOnlyDatastore datastore, String filePath) {
        try {
            new JsonDatastoreStorage(Paths.get(filePath))
                .saveDatastore(datastore, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveDatastore(new Datastore(), null));
    }
}
