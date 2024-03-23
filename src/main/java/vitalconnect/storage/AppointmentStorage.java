package vitalconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import vitalconnect.commons.exceptions.DataLoadingException;
import vitalconnect.model.Appointment;

/**
 * Represents the storage for appointment data.
 */
public interface AppointmentStorage {

    /**
     * Returns the file path of the appointment data file.
     *
     * @return The path to the appointment data file.
     */
    Path getAppointmentFilePath();

    /**
     * Reads the appointment data from the default file path.
     *
     * @return An {@code Optional} containing a list of {@code Appointment} if the data can be read successfully.
     * @throws DataLoadingException If there is an error loading data from the file.
     */
    Optional<List<Appointment>> readAppointments() throws DataLoadingException;

    /**
     * Reads the appointment data from a specified file path.
     *
     * @param filePath The path to the appointment data file.
     * @return An {@code Optional} containing a list of {@code Appointment} if the data can be read successfully.
     * @throws DataLoadingException If there is an error loading data from the file.
     */
    Optional<List<Appointment>> readAppointments(Path filePath) throws DataLoadingException;

    /**
     * Saves the given appointment data to the default file path.
     *
     * @param appointments The list of appointments to be saved, which cannot be null.
     * @throws IOException If there is an error writing to the file.
     */
    void saveAppointments(List<Appointment> appointments) throws IOException;

    /**
     * Saves the given appointment data to a specified file path.
     *
     * @param appointments The list of appointments to be saved, which cannot be null.
     * @param filePath The path to save the appointment data file.
     * @throws IOException If there is an error writing to the file.
     */
    void saveAppointments(List<Appointment> appointments, Path filePath) throws IOException;
}



