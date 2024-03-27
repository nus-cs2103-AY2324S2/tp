package vitalconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import vitalconnect.commons.core.GuiSettings;
import vitalconnect.model.Appointment;
import vitalconnect.model.Clinic;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClinicStorage clinicStorage = new JsonClinicStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonAppointmentStorage appointmentStorage = new JsonAppointmentStorage(getTempFilePath("appts"));
        storageManager = new StorageManager(clinicStorage, userPrefsStorage, appointmentStorage);
    }


    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void clinicReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClinicStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClinicStorageTest} class.
         */
        Clinic original = getTypicalClinic();
        storageManager.saveClinic(original);
        ReadOnlyClinic retrieved = storageManager.readClinic().get();
        assertEquals(original, new Clinic(retrieved));
    }


    @Test
    public void appointmentsReadSave() throws Exception {
        // Prepare original list of appointments
        List<Appointment> original = vitalconnect.testutil.TypicalAppointment.getTypicalAppointments();
        storageManager.saveAppointments(original);
        List<Appointment> retrieved = storageManager.readAppointments().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void getClinicFilePath() {
        assertNotNull(storageManager.getClinicFilePath());
    }

    @Test
    public void getAppointmentFilePath() {
        assertNotNull(storageManager.getAppointmentFilePath());
    }
}

