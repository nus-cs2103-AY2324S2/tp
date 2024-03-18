package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.NetConnect;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableNetConnectTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableNetConnectTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsNetConnect.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonNetConnect.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonNetConnect.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableNetConnect dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableNetConnect.class).get();
        NetConnect netConnectFromFile = dataFromFile.toModelType();
        NetConnect typicalPersonsNetConnect = TypicalPersons.getTypicalNetConnect();
        assertEquals(netConnectFromFile, typicalPersonsNetConnect);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableNetConnect dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableNetConnect.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableNetConnect dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableNetConnect.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNetConnect.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
