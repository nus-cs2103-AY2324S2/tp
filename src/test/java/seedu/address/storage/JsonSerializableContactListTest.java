package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ContactList;
import seedu.address.testutil.TypicalCourseMates;

public class JsonSerializableContactListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableContactListTest");
    private static final Path TYPICAL_COURSE_MATES_FILE = TEST_DATA_FOLDER.resolve("typicalCourseMatesContactList.json");
    private static final Path INVALID_COURSE_MATES_FILE = TEST_DATA_FOLDER.resolve("invalidCourseMateContactList.json");
    private static final Path DUPLICATE_COURSE_MATES_FILE = TEST_DATA_FOLDER.resolve("duplicateCourseMateContactList.json");

    @Test
    public void toModelType_typicalCourseMatesFile_success() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(TYPICAL_COURSE_MATES_FILE,
                JsonSerializableContactList.class).get();
        ContactList contactListFromFile = dataFromFile.toModelType();
        ContactList typicalCourseMatesContactList = TypicalCourseMates.getTypicalContactList();
        assertEquals(contactListFromFile, typicalCourseMatesContactList);
    }

    @Test
    public void toModelType_invalidCourseMateFile_throwsIllegalValueException() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(INVALID_COURSE_MATES_FILE,
                JsonSerializableContactList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCourseMates_throwsIllegalValueException() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_COURSE_MATES_FILE,
                JsonSerializableContactList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableContactList.MESSAGE_DUPLICATE_COURSE_MATE,
                dataFromFile::toModelType);
    }

}
