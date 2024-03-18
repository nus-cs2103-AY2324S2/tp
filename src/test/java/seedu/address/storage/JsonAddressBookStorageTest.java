package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.FIFTH_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.ReadOnlyAppointmentList;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Optional<ReadOnlyAppointmentList> readAppointmentList(String filePath) throws Exception {
        Path dummyFilePath = Paths.get("random.json");
        Path appointmentFilePath = Paths.get(filePath);
        return new JsonAddressBookStorage(dummyFilePath, appointmentFilePath)
                .readAppointmentList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }

    /**
     * Saves {@code appointmentList} at the specified {@code filePath}.
     */
    private void saveAppointmentList(ReadOnlyAppointmentList appointmentList, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
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
        assertThrows(DataLoadingException.class, () -> readAppointmentList("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAppointment_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidAppointmentList.json"));
    }

    @Test
    public void readAppointmentList_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidAndValidAppointmentList.json"));
    }

    @Test
    public void readAndSaveAppointmentList_allInOrder_success() throws Exception {
        Path abFilePath = testFolder.resolve("TempAddressBook.json");
        Path appointmentListfilePath = testFolder.resolve("TempAppointmentList.json");
        AppointmentList original = getTypicalAppointmentList();
        JsonAddressBookStorage jsonAddressBookStorage =
                new JsonAddressBookStorage(abFilePath, appointmentListfilePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAppointmentList(original, appointmentListfilePath);
        ReadOnlyAppointmentList readBack = jsonAddressBookStorage.readAppointmentList(appointmentListfilePath).get();
        assertEquals(original, new AppointmentList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAppointment(FIFTH_APPOINTMENT);
        jsonAddressBookStorage.saveAppointmentList(original, appointmentListfilePath);
        readBack = jsonAddressBookStorage.readAppointmentList(appointmentListfilePath).get();
        assertEquals(original, new AppointmentList(readBack));

        // Save and read without specifying file path
        original = getTypicalAppointmentList();
        original.addAppointment(FIFTH_APPOINTMENT);
        jsonAddressBookStorage.saveAppointmentList(original); // file path not specified
        readBack = jsonAddressBookStorage.readAppointmentList().get(); // file path not specified
        assertEquals(original, new AppointmentList(readBack));

    }

    @Test
    public void saveAppointmentList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAppointmentList(new AppointmentList(), null));
    }

    @Test
    public void saveAppointmentList_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAppointmentList(null, "SomeFile.json"));
    }

}
