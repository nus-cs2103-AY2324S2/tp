package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.InternshipData;
import seedu.address.testutil.TypicalInternships;

public class JsonSerializableInternshipDataTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableInternshipDataTest");
    private static final Path TYPICAL_INTERNSHIPS_FILE = TEST_DATA_FOLDER
            .resolve("typicalInternshipsInternshipData.json");
    private static final Path INVALID_INTERNSHIP_FILE = TEST_DATA_FOLDER
            .resolve("invalidInternshipInternshipData.json");
    private static final Path DUPLICATE_INTERNSHIP_FILE = TEST_DATA_FOLDER
            .resolve("duplicateInternshipInternshipData.json");

    @Test
    public void toModelType_typicalInternshipsFile_success() throws Exception {
        JsonSerializableInternshipData dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERNSHIPS_FILE,
                JsonSerializableInternshipData.class).get();
        InternshipData addressBookFromFile = dataFromFile.toModelType();
        InternshipData typicalInternshipsInternshipData = TypicalInternships.getTypicalInternshipData();
        assertEquals(addressBookFromFile, typicalInternshipsInternshipData);
    }

    @Test
    public void toModelType_invalidInternshipFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipData dataFromFile = JsonUtil.readJsonFile(INVALID_INTERNSHIP_FILE,
                JsonSerializableInternshipData.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternships_throwsIllegalValueException() throws Exception {
        JsonSerializableInternshipData dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERNSHIP_FILE,
                JsonSerializableInternshipData.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternshipData.MESSAGE_DUPLICATE_INTERNSHIP,
                dataFromFile::toModelType);
    }
}
