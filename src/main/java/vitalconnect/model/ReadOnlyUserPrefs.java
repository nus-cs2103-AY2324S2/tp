package vitalconnect.model;

import java.nio.file.Path;

import vitalconnect.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getClinicFilePath();

}
