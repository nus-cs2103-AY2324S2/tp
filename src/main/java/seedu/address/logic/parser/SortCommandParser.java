package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final ArrayList<String> AllowedPrefixes = new ArrayList<>() {
        {
            add(PREFIX_NAME.getPrefix());
        }
    };

    /**
     * Checks if the given prefix is allowed in choosing an attribute for sorting.
     */
    public static boolean isAllowedPrefix(String prefix) {
        return AllowedPrefixes.contains(prefix);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String prefix = args.trim();
        if (prefix.isEmpty() || !PREFIX_NAME.getPrefix().equals(prefix)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(prefix);
    }
}
