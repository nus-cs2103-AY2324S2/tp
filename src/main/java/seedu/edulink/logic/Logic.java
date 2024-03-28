package seedu.edulink.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.edulink.commons.core.GuiSettings;
import seedu.edulink.logic.commands.CommandResult;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.ReadOnlyAddressBook;
import seedu.edulink.model.student.Student;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.edulink.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Student> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
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
