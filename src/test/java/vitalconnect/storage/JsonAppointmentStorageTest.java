package vitalconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import vitalconnect.model.Appointment;

public class JsonAppointmentStorageTest {

    @TempDir
    public Path testFolder;

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void readAppointments_nullFilePath_throwsNullPointerException() {
        JsonAppointmentStorage storage = new JsonAppointmentStorage(null);
        assertThrows(NullPointerException.class, storage::readAppointments);
    }

    @Test
    public void readAppointments_missingFile_emptyResult() throws Exception {
        JsonAppointmentStorage storage = new JsonAppointmentStorage(getTempFilePath("NonExistentFile.json"));
        assertFalse(storage.readAppointments().isPresent());
    }

    @Test
    public void readAndSaveAppointments_allInOrder_success() throws Exception {
        Path filePath = getTempFilePath("TempAppointments.json");
        List<Appointment> original = vitalconnect.testutil.TypicalAppointment.getTypicalAppointments();
        JsonAppointmentStorage jsonAppointmentStorage = new JsonAppointmentStorage(filePath);

        // Save in new file and read back
        jsonAppointmentStorage.saveAppointments(original, filePath);
        List<Appointment> readBack = jsonAppointmentStorage.readAppointments(filePath).get();
        assertEquals(original, readBack);
    }

    @Test
    public void saveAppointments_nullAppointments_throwsNullPointerException() {
        JsonAppointmentStorage storage = new JsonAppointmentStorage(getTempFilePath("SomeFile.json"));
        assertThrows(NullPointerException.class, () -> storage.saveAppointments(null,
                getTempFilePath("SomeFile.json")));
    }

    @Test
    public void saveAppointments_nullFilePath_throwsNullPointerException() {
        JsonAppointmentStorage storage = new JsonAppointmentStorage(null);
        assertThrows(NullPointerException.class, () ->
                storage.saveAppointments(vitalconnect.testutil.TypicalAppointment.getTypicalAppointments(), null));
    }
}

