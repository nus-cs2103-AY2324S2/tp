package vitalconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import vitalconnect.commons.exceptions.IllegalValueException;
import vitalconnect.commons.util.JsonUtil;
import vitalconnect.model.Appointment;
import vitalconnect.testutil.TypicalAppointment;

public class JsonSerializableAppointmentTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableAppointmentTest");
    private static final Path TYPICAL_APPOINTMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalAppointments.json");
    private static final Path INVALID_APPOINTMENT_FILE = TEST_DATA_FOLDER.resolve("invalidAppointment.json");

    @Test
    public void toModelType_typicalAppointmentsFile_success() throws Exception {
        JsonSerializableAppointment dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENTS_FILE,
                JsonSerializableAppointment.class).get();
        List<Appointment> appointmentsFromFile = dataFromFile.toModelType();
        List<Appointment> typicalAppointments = TypicalAppointment.getTypicalAppointments();
        assertEquals(appointmentsFromFile, typicalAppointments);
    }


    @Test
    public void toModelType_invalidAppointmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAppointment dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableAppointment.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}

