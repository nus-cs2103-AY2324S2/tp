package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.commons.util.JsonUtil;
//import seedu.address.model.AddressBook;
//import seedu.address.testutil.TypicalPersons;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

}
