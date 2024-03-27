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
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path clientFilePath = Paths.get("data", "client.json");
    private Path housekeeperFilePath = Paths.get("data", "housekeeper.json");

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
        setClientFilePath(newUserPrefs.getClientFilePath());
        setHousekeeperFilePath(newUserPrefs.getHousekeeperFilePath());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public Path getClientFilePath() {
        return clientFilePath;
    }

    public Path getHousekeeperFilePath() {
        return housekeeperFilePath;
    }

    public void setClientFilePath(Path clientFilePath) {
        requireNonNull(clientFilePath);
        this.clientFilePath = clientFilePath;
    }

    public void setHousekeeperFilePath(Path housekeeperFilePath) {
        requireNonNull(housekeeperFilePath);
        this.housekeeperFilePath = housekeeperFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
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
                && clientFilePath.equals(otherUserPrefs.clientFilePath)
                && housekeeperFilePath.equals(otherUserPrefs.housekeeperFilePath)
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, clientFilePath, housekeeperFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal client data file location : " + clientFilePath);
        sb.append("\nLocal housekeeper data file location : " + housekeeperFilePath);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }

}
