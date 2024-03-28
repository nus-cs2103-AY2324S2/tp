package scrolls.elder.logic.parser;

import static scrolls.elder.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static scrolls.elder.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scrolls.elder.commons.core.LogsCenter;
import scrolls.elder.logic.commands.AddCommand;
import scrolls.elder.logic.commands.ClearCommand;
import scrolls.elder.logic.commands.Command;
import scrolls.elder.logic.commands.DeleteCommand;
import scrolls.elder.logic.commands.EditCommand;
import scrolls.elder.logic.commands.ExitCommand;
import scrolls.elder.logic.commands.FindCommand;
import scrolls.elder.logic.commands.HelpCommand;
import scrolls.elder.logic.commands.ListCommand;
import scrolls.elder.logic.commands.LogAddCommand;
import scrolls.elder.logic.commands.PairCommand;
import scrolls.elder.logic.commands.UnpairCommand;
import scrolls.elder.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

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

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD_DELETE:
        case DeleteCommand.COMMAND_WORD_DEL:
        case DeleteCommand.COMMAND_WORD_RM:
        case DeleteCommand.COMMAND_WORD_REMOVE:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case PairCommand.COMMAND_WORD:
            return new PairCommandParser().parse(arguments);

        case UnpairCommand.COMMAND_WORD:
            return new UnpairCommandParser().parse(arguments);

        case LogAddCommand.COMMAND_WORD:
            return new LogAddCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
