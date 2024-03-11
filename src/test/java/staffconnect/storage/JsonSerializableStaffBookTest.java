package staffconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static staffconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.commons.util.JsonUtil;
import staffconnect.model.StaffBook;
import staffconnect.testutil.TypicalPersons;

public class JsonSerializableStaffBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStaffBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsStaffBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonStaffBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonStaffBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableStaffBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableStaffBook.class).get();
        StaffBook staffBookFromFile = dataFromFile.toModelType();
        StaffBook typicalPersonsStaffBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(staffBookFromFile, typicalPersonsStaffBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStaffBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableStaffBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableStaffBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableStaffBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStaffBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
