package seedu.realodex.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.realodex.commons.core.GuiSettings;
import seedu.realodex.logic.commands.CommandResult;
import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.person.Person;

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
     * Returns the Realodex.
     *
     * @see seedu.realodex.model.Model#getRealodex()
     */
    ReadOnlyRealodex getRealodex();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' realodex file path.
     */
    Path getRealodexFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
