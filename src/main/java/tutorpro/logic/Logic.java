package tutorpro.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import tutorpro.commons.core.GuiSettings;
import tutorpro.logic.commands.CommandResult;
import tutorpro.logic.commands.exceptions.CommandException;
import tutorpro.logic.parser.exceptions.ParseException;
import tutorpro.model.Model;
import tutorpro.model.ReadOnlyAddressBook;
<<<<<<< HEAD
=======
import tutorpro.model.Model;
>>>>>>> 8b687dab64e7b9193c9522c83dedd53efb089b6f
import tutorpro.model.person.Person;

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
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

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
