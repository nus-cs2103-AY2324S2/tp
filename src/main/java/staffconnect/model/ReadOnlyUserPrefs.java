package staffconnect.model;

import java.nio.file.Path;

import staffconnect.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStaffConnectFilePath();

}
