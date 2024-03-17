package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TaskMasterPro;
import seedu.address.testutil.TypicalEmployees;

public class JsonSerializableTaskMasterProTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskMasterProTest");
    private static final Path TYPICAL_EMPLOYEES_FILE = TEST_DATA_FOLDER.resolve("typicalEmployeesTaskMasterPro.json");
    private static final Path INVALID_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("invalidEmployeeTaskMasterPro.json");
    private static final Path DUPLICATE_EMPLOYEE_FILE = TEST_DATA_FOLDER.resolve("duplicateEmployeeTaskMasterPro.json");

    @Test
    public void toModelType_typicalEmployeesFile_success() throws Exception {
        JsonSerializableTaskMasterPro dataFromFile = JsonUtil.readJsonFile(TYPICAL_EMPLOYEES_FILE,
                JsonSerializableTaskMasterPro.class).get();
        TaskMasterPro taskMasterProFromFile = dataFromFile.toModelType();
        TaskMasterPro typicalEmployeesTaskMasterPro = TypicalEmployees.getTypicalTaskMasterPro();
        assertEquals(taskMasterProFromFile, typicalEmployeesTaskMasterPro);
    }

    @Test
    public void toModelType_invalidEmployeeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskMasterPro dataFromFile = JsonUtil.readJsonFile(INVALID_EMPLOYEE_FILE,
                JsonSerializableTaskMasterPro.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmployees_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskMasterPro dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMPLOYEE_FILE,
                JsonSerializableTaskMasterPro.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskMasterPro.MESSAGE_DUPLICATE_EMPLOYEE,
                dataFromFile::toModelType);
    }
}
