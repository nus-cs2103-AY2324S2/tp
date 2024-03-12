package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalCourseMates;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_COURSE_MATES_FILE = TEST_DATA_FOLDER.resolve("typicalCourseMatesAddressBook.json");
    private static final Path INVALID_COURSE_MATES_FILE = TEST_DATA_FOLDER.resolve("invalidCourseMateAddressBook.json");
    private static final Path DUPLICATE_COURSE_MATES_FILE = TEST_DATA_FOLDER.resolve("duplicateCourseMateAddressBook.json");

    @Test
    public void toModelType_typicalCourseMatesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_COURSE_MATES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalCourseMatesAddressBook = TypicalCourseMates.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalCourseMatesAddressBook);
    }

    @Test
    public void toModelType_invalidCourseMateFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_COURSE_MATES_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCourseMates_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_COURSE_MATES_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_COURSE_MATE,
                dataFromFile::toModelType);
    }

}
