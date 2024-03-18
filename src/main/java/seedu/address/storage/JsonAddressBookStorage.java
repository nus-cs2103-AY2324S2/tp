package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.appointment.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentList;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path personsFilePath;

    private Path appointmentFilePath;

    /**
     * Creates a {@code JsonAddressBookStorage} with the given file paths.
     * @param personsFilePath the file path for the person data. Cannot be null.
     * @param AppointmentFilePath the file path for the appointment list data. Can be null.
     */

    public JsonAddressBookStorage(Path personsFilePath, Path AppointmentFilePath) {
        this.personsFilePath = personsFilePath;
        this.appointmentFilePath = AppointmentFilePath;
    }

    public JsonAddressBookStorage(Path personsFilePath) {
        this.personsFilePath = personsFilePath;
    }

    public Path getAddressBookFilePath() {
        return personsFilePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(personsFilePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, personsFilePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

    /**
     * Saves the given {@link ReadOnlyAppointmentList} to the storage.
     *
     * @param appointmentList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAppointmentList(ReadOnlyAppointmentList appointmentList) throws IOException {
        saveAppointmentList(appointmentList, personsFilePath);
    }

    /**
     * @see #saveAppointmentList(ReadOnlyAppointmentList)
     */
    @Override
    public void saveAppointmentList(ReadOnlyAppointmentList appointmentList, Path filePath) throws IOException {
        requireNonNull(appointmentList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAppointmentList(appointmentList), filePath);
    }



}
