package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalStartups;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_STARTUPS_FILE = TEST_DATA_FOLDER.resolve("typicalStartupAddressBook.json");
    private static final Path INVALID_STARTUP_FILE = TEST_DATA_FOLDER.resolve("invalidStartupAddressBook.json");
    private static final Path DUPLICATE_STARTUP_FILE = TEST_DATA_FOLDER.resolve("duplicateStartupAddressBook.json");

    @Test
    public void toModelType_typicalStartupsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STARTUPS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalStartupsAddressBook = TypicalStartups.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalStartupsAddressBook);
    }

    @Test
    public void toModelType_invalidStartupFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_STARTUP_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStartups_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STARTUP_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_STARTUP,
                dataFromFile::toModelType);
    }

}
