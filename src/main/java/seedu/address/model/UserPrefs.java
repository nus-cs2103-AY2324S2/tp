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
    private Path netConnectFilePath = Paths.get("data", "netconnect.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

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
        setNetConnectFilePath(newUserPrefs.getNetConnectFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getNetConnectFilePath() {
        return netConnectFilePath;
    }

    public void setNetConnectFilePath(Path netConnectFilePath) {
        requireNonNull(netConnectFilePath);
        this.netConnectFilePath = netConnectFilePath;
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
                && netConnectFilePath.equals(otherUserPrefs.netConnectFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, netConnectFilePath);
    }

    @Override
    public String toString() {
        String sb = "Gui Settings : " + guiSettings
                + "\nLocal data file location : " + netConnectFilePath;
        return sb;
    }

}
