package vitalconnect.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import vitalconnect.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path clinicFilePath = Paths.get("data" , "clinic.json");
    private Path appointmentFilePath = Paths.get("data", "appointments.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setClinicFilePath(newUserPrefs.getClinicFilePath());
        setAppointmentFilePath(newUserPrefs.getAppointmentFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getClinicFilePath() {
        return clinicFilePath;
    }

    public void setClinicFilePath(Path clinicFilePath) {
        requireNonNull(clinicFilePath);
        this.clinicFilePath = clinicFilePath;
    }

    public Path getAppointmentFilePath() {
        return appointmentFilePath;
    }

    public void setAppointmentFilePath(Path appointmentFilePath) {
        requireNonNull(appointmentFilePath);
        this.appointmentFilePath = appointmentFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && clinicFilePath.equals(otherUserPrefs.clinicFilePath)
                && appointmentFilePath.equals(otherUserPrefs.appointmentFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, clinicFilePath, appointmentFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : ").append(guiSettings);
        sb.append("\nLocal clinic data file location : ").append(clinicFilePath);
        sb.append("\nLocal appointment data file location : ").append(appointmentFilePath);
        return sb.toString();
    }
}
