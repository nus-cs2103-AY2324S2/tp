package seedu.realodex.model;

import java.nio.file.Path;

import seedu.realodex.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getRealodexFilePath();

}
