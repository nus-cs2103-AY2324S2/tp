package vitalConnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalConnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import vitalConnect.commons.exceptions.IllegalValueException;
import vitalConnect.commons.util.JsonUtil;
import vitalConnect.model.Clinic;
import vitalConnect.testutil.TypicalPersons;

public class JsonSerializableClinicTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableClinicTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsClinic.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonClinic.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonClinic.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableClinic dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableClinic.class).get();
        Clinic clinicFromFile = dataFromFile.toModelType();
        Clinic typicalPersonsClinic = TypicalPersons.getTypicalClinic();
        assertEquals(clinicFromFile, typicalPersonsClinic);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableClinic dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableClinic.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableClinic dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableClinic.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableClinic.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
