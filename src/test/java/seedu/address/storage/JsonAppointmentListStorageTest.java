package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.FIFTH_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.ReadOnlyAppointmentList;

public class JsonAppointmentListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAppointmentListStorageTest");

    @TempDir
    public Path testFolder;

    private Optional<ReadOnlyAppointmentList> readAppointmentList(String filePath) throws Exception {
        Path appointmentFilePath = Paths.get(filePath);
        return new JsonAppointmentListStorage(appointmentFilePath)
                .readAppointmentList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    /**
     * Saves {@code appointmentList} at the specified {@code filePath}.
     */
    private void saveAppointmentList(ReadOnlyAppointmentList appointmentList, String filePath) {
        try {
            new JsonAppointmentListStorage(Paths.get(filePath))
                    .saveAppointmentList(appointmentList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void readAppointmentList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAppointmentList(null));
    }
    @Test
    public void readAppointment_missingFile_emptyResult() throws Exception {
        assertFalse(readAppointmentList("NonExistentFile.json").isPresent());
    }

    @Test
    public void readAppointment_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAppointmentList("notJsonFormatAppointmentList.json"));
    }

    @Test
    public void readAppointment_invalidAppointmentList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAppointmentList("invalidAppointmentList.json"));
    }

    @Test
    public void readAppointmentList_invalidAndValidAppointmentList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAppointmentList("invalidAndValidAppointmentList.json"));
    }

    @Test
    public void readAndSaveAppointmentList_allInOrder_success() throws Exception {
        Path appointmentListfilePath = testFolder.resolve("TempAppointmentList.json");
        AppointmentList original = getTypicalAppointmentList();
        JsonAppointmentListStorage jsonAppointmentListStorage =
                new JsonAppointmentListStorage(appointmentListfilePath);

        // Save in new file and read back
        jsonAppointmentListStorage.saveAppointmentList(original, appointmentListfilePath);
        ReadOnlyAppointmentList readBack = jsonAppointmentListStorage.readAppointmentList(appointmentListfilePath).get();
        assertEquals(original, new AppointmentList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAppointment(FIFTH_APPOINTMENT);
        jsonAppointmentListStorage.saveAppointmentList(original, appointmentListfilePath);
        readBack = jsonAppointmentListStorage.readAppointmentList(appointmentListfilePath).get();
        assertEquals(original, new AppointmentList(readBack));

        // Save and read without specifying file path
        original = getTypicalAppointmentList();
        original.addAppointment(FIFTH_APPOINTMENT);
        jsonAppointmentListStorage.saveAppointmentList(original); // file path not specified
        readBack = jsonAppointmentListStorage.readAppointmentList().get(); // file path not specified
        assertEquals(original, new AppointmentList(readBack));

    }

    @Test
    public void saveAppointmentList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAppointmentList(new AppointmentList(), null));
    }

    @Test
    public void saveAppointmentList_nullPatientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAppointmentList(null, "SomeFile.json"));
    }

}
