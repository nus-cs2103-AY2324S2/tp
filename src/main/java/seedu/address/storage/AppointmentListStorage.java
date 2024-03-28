package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.ReadOnlyAppointmentList;

/**
 * Represents a storage for {@link AppointmentList}.
 */
public interface AppointmentListStorage {


    /**
     * Returns the file path of the data file.
     */
    Path getAppointmentListFilePath();

    /**
     * Returns AppointmentList data as a {@link ReadOnlyAppointmentList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyAppointmentList> readAppointmentList() throws DataLoadingException;

    /**
     * @see #getAppointmentListFilePath()
     */
    Optional<ReadOnlyAppointmentList> readAppointmentList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyAppointmentList} to the storage.
     * @param appointmentList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAppointmentList(ReadOnlyAppointmentList appointmentList) throws IOException;
    /**
     * @see #saveAppointmentList(ReadOnlyAppointmentList)
     */
    void saveAppointmentList(ReadOnlyAppointmentList appointmentList, Path filePath) throws IOException;

}
