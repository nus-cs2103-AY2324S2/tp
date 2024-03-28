package scrolls.elder.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import scrolls.elder.commons.exceptions.IllegalValueException;
import scrolls.elder.commons.util.JsonUtil;
import scrolls.elder.model.Datastore;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.TypicalDatastore;

public class JsonSerializableDatastoreTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDatastoreTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsDatastore.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonDatastore.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonDatastore.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableDatastore dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableDatastore.class).get();
        Datastore datastoreFromFile = dataFromFile.toModelType();
        Datastore typicalPersonsDatastore = TypicalDatastore.getTypicalDatastore();
        assertEquals(datastoreFromFile, typicalPersonsDatastore);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDatastore dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializableDatastore.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableDatastore dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializableDatastore.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableDatastore.MESSAGE_DUPLICATE_PERSON,
            dataFromFile::toModelType);
    }

}
