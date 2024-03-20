package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableGroupListTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableGroupListTest");
    private static final Path INVALID_GROUPS_FILE =
            TEST_DATA_FOLDER.resolve("invalidGroupGroupList.json");
    private static final Path DUPLICATE_GROUPS_FILE =
            TEST_DATA_FOLDER.resolve("duplicateGroupGroupList.json");

    @Test
    public void toModelType_invalidGroupFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGroupList dataFromFile = JsonUtil.readJsonFile(INVALID_GROUPS_FILE,
                JsonSerializableGroupList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGroups_throwsIllegalValueException() throws Exception {
        JsonSerializableGroupList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GROUPS_FILE,
                JsonSerializableGroupList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableGroupList.MESSAGE_DUPLICATE_GROUP,
                dataFromFile::toModelType);
    }
}
