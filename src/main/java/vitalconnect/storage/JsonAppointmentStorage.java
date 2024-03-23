package vitalconnect.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import vitalconnect.commons.exceptions.DataLoadingException;
import vitalconnect.commons.exceptions.IllegalValueException;
import vitalconnect.commons.util.JsonUtil;
import vitalconnect.model.Appointment;


/**
 * A class to access Appointment data stored as a JSON file on the hard disk.
 */
public class JsonAppointmentStorage implements AppointmentStorage {
    private final Path filePath;

    /**
     * Constructs a {@code JsonAppointmentStorage} with the given file path.
     *
     * @param filePath Path to the file where appointment data is stored.
     */
    public JsonAppointmentStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path where the appointment data is stored.
     *
     * @return The file path of the appointment data.
     */
    @Override
    public Path getAppointmentFilePath() {
        return filePath;
    }

    /**
     * Reads the appointment data from the default storage file.
     *
     * @return An {@code Optional} contain a list of appointments if file exists, or {@code Optional.empty()} otherwise.
     * @throws DataLoadingException If there is an error reading from the file.
     */
    @Override
    public Optional<List<Appointment>> readAppointments() throws DataLoadingException {
        return readAppointments(filePath);
    }

    /**
     * Reads the appointment data from a specified file path.
     *
     * @param filePath Path to the file from which appointment data should be read.
     * @return An {@code Optional} contain a list of appointments if file exists or {@code Optional.empty()} otherwise.
     * @throws DataLoadingException If there is an error reading from the file.
     */
    @Override
    public Optional<List<Appointment>> readAppointments(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);
        Optional<JsonSerializableAppointment> jsonAppointment = JsonUtil.readJsonFile(
                filePath, JsonSerializableAppointment.class);
        if (!jsonAppointment.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAppointment.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataLoadingException(ive);
        }
    }

    /**
     * Saves the provided list of appointments to the default storage file.
     *
     * @param appointments The list of appointments to save.
     * @throws IOException If there is an error writing to the file.
     */
    @Override
    public void saveAppointments(List<Appointment> appointments) throws IOException {
        saveAppointments(appointments, filePath);
    }

    /**
     * Saves the provided list of appointments to a specified file path.
     *
     * @param appointments The list of appointments to save.
     * @param filePath Path to the file where appointment data should be saved.
     * @throws IOException If there is an error writing to the file.
     */
    @Override
    public void saveAppointments(List<Appointment> appointments, Path filePath) throws IOException {
        requireNonNull(appointments);
        requireNonNull(filePath);

        List<JsonAdaptedAppointment> jsonAdaptedAppointments = appointments.stream()
                .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList());

        JsonUtil.saveJsonFile(new JsonSerializableAppointment(jsonAdaptedAppointments), filePath);
    }
}
