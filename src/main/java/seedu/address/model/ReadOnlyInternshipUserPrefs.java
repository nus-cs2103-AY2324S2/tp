package seedu.address.model;

import seedu.address.commons.core.GuiSettings;

import java.nio.file.Path;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyInternshipUserPrefs {

    GuiSettings getGuiSettings();

    Path getInternshipDataFilePath();

}
