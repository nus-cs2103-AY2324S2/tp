package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CodeConnect;
import seedu.address.testutil.TypicalContacts;

public class JsonSerializableCodeConnectTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCodeConnectTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalContactCodeConnect.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidContactCodeConnect.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateContactCodeConnect.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableCodeConnect dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableCodeConnect.class).get();
        CodeConnect codeConnectFromFile = dataFromFile.toModelType();
        CodeConnect typicalPersonsCodeConnect = TypicalContacts.getTypicalCodeConnect();
        assertEquals(codeConnectFromFile, typicalPersonsCodeConnect);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCodeConnect dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableCodeConnect.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableCodeConnect dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableCodeConnect.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCodeConnect.MESSAGE_DUPLICATE_CONTACT,
                dataFromFile::toModelType);
    }

}
