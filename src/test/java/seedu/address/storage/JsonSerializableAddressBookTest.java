package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalClients;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsAddressBook.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientAddressBook.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientAddressBook.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalClientsAddressBook = TypicalClients.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalClientsAddressBook);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

}
