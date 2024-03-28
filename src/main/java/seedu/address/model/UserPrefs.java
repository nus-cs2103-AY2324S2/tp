package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path patientListFilePath = Paths.get("data" , "patientList.json");

    private Path appointmentListFilePath = Paths.get("data", "appointmentList.json");

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
        setPatientListFilePath(newUserPrefs.getPatientListFilePath());
        setAppointmentListFilePath(newUserPrefs.getAppointmentListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPatientListFilePath() {
        return patientListFilePath;
    }

    public Path getAppointmentListFilePath() {
        return appointmentListFilePath;
    }

    public void setPatientListFilePath(Path patientListFilePath) {
        requireNonNull(patientListFilePath);
        this.patientListFilePath = patientListFilePath;
    }

    public void setAppointmentListFilePath(Path appointmentListFilePath) {
        requireNonNull(appointmentListFilePath);
        this.appointmentListFilePath = appointmentListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && patientListFilePath.equals(otherUserPrefs.patientListFilePath)
                && appointmentListFilePath.equals(otherUserPrefs.appointmentListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, patientListFilePath, appointmentListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + patientListFilePath);
        sb.append("\nLocal appointment list file location : " + appointmentListFilePath);
        return sb.toString();
    }

}
