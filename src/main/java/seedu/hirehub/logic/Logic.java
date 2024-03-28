package seedu.hirehub.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.hirehub.commons.core.GuiSettings;
import seedu.hirehub.logic.commands.CommandResult;
import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.logic.parser.exceptions.ParseException;
import seedu.hirehub.model.ReadOnlyAddressBook;
import seedu.hirehub.model.job.Job;
import seedu.hirehub.model.person.Person;

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
     * Returns the AddressBook.
     *
     * @see seedu.hirehub.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();
    /** Returns an unmodifiable view of the filtered list of jobs */

    ObservableList<Job> getFilteredJobList();

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
