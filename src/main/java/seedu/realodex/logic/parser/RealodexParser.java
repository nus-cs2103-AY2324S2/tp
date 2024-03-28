package seedu.realodex.logic.parser;

import static seedu.realodex.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realodex.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.realodex.commons.core.LogsCenter;
import seedu.realodex.logic.commands.AddCommand;
import seedu.realodex.logic.commands.ClearCommand;
import seedu.realodex.logic.commands.Command;
import seedu.realodex.logic.commands.DeleteCommand;
import seedu.realodex.logic.commands.EditCommand;
import seedu.realodex.logic.commands.ExitCommand;
import seedu.realodex.logic.commands.FilterCommand;
import seedu.realodex.logic.commands.HelpCommand;
import seedu.realodex.logic.commands.ListCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class RealodexParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(RealodexParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        //just for checking if help is called so that trimming is possible
        String nonFinalArguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        boolean isHelp = false;
        if (nonFinalArguments.trim().equalsIgnoreCase("help")) {
            isHelp = true;
        }

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            if (isHelp) {
                return new HelpCommandParser().parse(commandWord);
            }
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            if (isHelp) {
                return new HelpCommandParser().parse(commandWord);
            }
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            if (isHelp) {
                return new HelpCommandParser().parse(commandWord);
            }
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            if (isHelp) {
                return new HelpCommandParser().parse(commandWord);
            }
            return new ClearCommand();

        case FilterCommand.COMMAND_WORD:
            if (isHelp) {
                return new HelpCommandParser().parse(commandWord);
            }
            return new FilterCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            if (isHelp) {
                return new HelpCommandParser().parse(commandWord);
            }
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand("");

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
