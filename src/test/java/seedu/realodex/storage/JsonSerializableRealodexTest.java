package seedu.realodex.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.realodex.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.realodex.commons.exceptions.IllegalValueException;
import seedu.realodex.commons.util.JsonUtil;
import seedu.realodex.model.Realodex;
import seedu.realodex.testutil.TypicalPersons;

public class JsonSerializableRealodexTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRealodexTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsRealodex.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonRealodex.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonRealodex.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableRealodex dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                                                                      JsonSerializableRealodex.class).get();
        Realodex realodexFromFile = dataFromFile.toModelType();
        Realodex typicalPersonsRealodex = TypicalPersons.getTypicalRealodex();
        assertEquals(realodexFromFile, typicalPersonsRealodex);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRealodex dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                                                                      JsonSerializableRealodex.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableRealodex dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                                                                      JsonSerializableRealodex.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRealodex.MESSAGE_DUPLICATE_PERSON,
                     dataFromFile::toModelType);
    }

}
