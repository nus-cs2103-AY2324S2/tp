package seedu.address.model;

import seedu.address.commons.core.GuiSettings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents User's preferences.
 */
public class InternshipUserPrefs implements ReadOnlyInternshipUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path internshipDataFilePath = Paths.get("data" , "internshipdata.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public InternshipUserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public InternshipUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyInternshipUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getInternshipDataFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getInternshipDataFilePath() {
        return internshipDataFilePath;
    }

    public void setAddressBookFilePath(Path internshipDataFilePath) {
        requireNonNull(internshipDataFilePath);
        this.internshipDataFilePath = internshipDataFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipUserPrefs)) {
            return false;
        }

        InternshipUserPrefs otherUserPrefs = (InternshipUserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && internshipDataFilePath.equals(otherUserPrefs.internshipDataFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, internshipDataFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + internshipDataFilePath);
        return sb.toString();
    }

}
