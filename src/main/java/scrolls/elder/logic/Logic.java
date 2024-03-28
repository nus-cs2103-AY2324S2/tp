package scrolls.elder.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import scrolls.elder.commons.core.GuiSettings;
import scrolls.elder.logic.commands.CommandResult;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.logic.parser.exceptions.ParseException;
import scrolls.elder.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredVolunteerList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredBefriendeeList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getDatastoreFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
