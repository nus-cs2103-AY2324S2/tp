package scrolls.elder.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import scrolls.elder.commons.core.GuiSettings;
import scrolls.elder.model.person.Person;

/**
 * The API of the Model component. Controls in-memory data of the application.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL = unused -> true;

    //// UserPrefs getters and setters

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //// Datastore getters and setters

    /**
     * Returns the user prefs' datastore file path.
     */
    Path getDatastoreFilePath();

    /**
     * Sets the user prefs' datastore file path.
     */
    void setDatastoreFilePath(Path datastoreFilePath);

    //// Datastore getters and setters

    /**
     * Returns a readonly view of the Datastore
     */
    ReadOnlyDatastore getDatastore();

    /**
     * Returns a mutable view of the Datastore
     */
    Datastore getMutableDatastore();

    /**
     * Replaces Datastore with the data in {@code datastore}.
     */
    void setDatastore(ReadOnlyDatastore datastore);

}
