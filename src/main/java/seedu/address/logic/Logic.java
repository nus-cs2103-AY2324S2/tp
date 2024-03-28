package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.group.Group;

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
     * Returns the ContactList.
     *
     * @see seedu.address.model.Model#getContactList()
     */
    ReadOnlyContactList getContactList();

    /** Returns an unmodifiable view of the filtered list of course mates */
    ObservableList<CourseMate> getFilteredCourseMateList();

    /** Returns an unmodifiable view of the filtered list of groups */
    ObservableList<Group> getFilteredGroupList();

    /** Returns the most recently processed course mate */
    CourseMate getRecentlyProcessedCourseMate();

    /** Sets the most recently processed course mate */
    void setRecentlyProcessedCourseMate(CourseMate courseMate);

    /**
     * Returns the user prefs' contact list file path.
     */
    Path getContactListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
