package seedu.address.logic.commands.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.SetCourseCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;

/**
 * A container for {@code Command} word specific utility functions.
 */
public class CommandWordUtil {
    private static final String[] ALL_COMMAND_WORDS = {
        AddPersonCommand.COMMAND_WORD,
        ClearCommand.COMMAND_WORD,
        DeletePersonCommand.COMMAND_WORD,
        EditPersonCommand.COMMAND_WORD,
        ExitCommand.COMMAND_WORD,
        FindPersonCommand.COMMAND_WORD,
        HelpCommand.COMMAND_WORD,
        ListPersonCommand.COMMAND_WORD,
        MarkAttendanceCommand.COMMAND_WORD,
        SetCourseCommand.COMMAND_WORD,
        UnmarkAttendanceCommand.COMMAND_WORD,
    };

    /**
     * Returns true if the word is a command word, after removing non-word characters.
     */
    public static boolean isCommandWord(String word) {
        requireNonNull(word);
        String strippedWord = word.replaceAll("\\W", "");
        return Arrays
                .asList(ALL_COMMAND_WORDS)
                .contains(strippedWord);
    }
}
