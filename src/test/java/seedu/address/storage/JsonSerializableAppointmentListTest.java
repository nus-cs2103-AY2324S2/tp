package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.testutil.TypicalAppointments;



public class JsonSerializableAppointmentListTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableAppointmentListTest");
    private static final Path TYPICAL_APPOINTMENT_LIST_FILE =
            TEST_DATA_FOLDER.resolve("typicalAppointmentList.json");
    private static final Path INVALID_APPOINTMENT_LIST =
            TEST_DATA_FOLDER.resolve("invalidAppointmentList.json");
    private static final Path DUPLICATE_APPOINTMENT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateAppointmentList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAppointmentList dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENT_LIST_FILE,
                JsonSerializableAppointmentList.class).get();
        AppointmentList addressBookFromFile = dataFromFile.toModelType();
        AppointmentList typicalPersonsAddressBook = TypicalAppointments.getTypicalAppointmentList();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAppointmentList dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_LIST,
                JsonSerializableAppointmentList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAppointmentList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENT_FILE,
                JsonSerializableAppointmentList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAppointmentList.MESSAGE_DUPLICATE_APPOINTMENT,
                dataFromFile::toModelType);
    }

}
