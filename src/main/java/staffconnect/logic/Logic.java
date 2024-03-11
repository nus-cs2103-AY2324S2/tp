package staffconnect.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import staffconnect.commons.core.GuiSettings;
import staffconnect.logic.commands.CommandResult;
import staffconnect.logic.commands.exceptions.CommandException;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.ReadOnlyStaffConnect;
import staffconnect.model.person.Person;

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

    /**
     * Returns the StaffBook.
     *
     * @see staffconnect.model.Model#getAddressBook()
     */
    ReadOnlyStaffConnect getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' staff book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
