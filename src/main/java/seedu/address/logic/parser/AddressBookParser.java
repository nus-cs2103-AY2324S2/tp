package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMaintainerCommand;
import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.logic.commands.AddSupplierCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditMaintainerCommand;
import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.commands.PinCommand;
import seedu.address.logic.commands.RateCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnpinCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        case AddStaffCommand.COMMAND_WORD:
            return new AddStaffCommandParser().parse(arguments);

        case AddSupplierCommand.COMMAND_WORD:
            return new AddSupplierCommandParser().parse(arguments);

        case AddMaintainerCommand.COMMAND_WORD:
            return new AddMaintainerCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case EditStaffCommand.COMMAND_WORD:
            return new EditStaffCommandParser().parse(arguments);

        case EditSupplierCommand.COMMAND_WORD:
            return new EditSupplierCommandParser().parse(arguments);

        case EditMaintainerCommand.COMMAND_WORD:
            return new EditMaintainerCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
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
            return new HelpCommandParser().parse(arguments);

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case NoteCommand.COMMAND_WORD:
            return new NoteCommandParser().parse(arguments);

        case RateCommand.COMMAND_WORD:
            return new RateCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case PinCommand.COMMAND_WORD:
            return new PinCommandParser().parse(arguments);

        case UnpinCommand.COMMAND_WORD:
            return new UnpinCommandParser().parse(arguments);

        case RemindCommand.COMMAND_WORD:
            return new RemindCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
