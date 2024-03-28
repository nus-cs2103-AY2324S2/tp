package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    private static final Set<String> VALID_FIELDS = new HashSet<>(Arrays.asList(
            "email", "major", "name", "phone", "star"
    ));
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * @return a {@code SortCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] words = trimmedArgs.split("\\s+");

        if (words.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String field = words[0];
        if (!VALID_FIELDS.contains(field)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String direction = words[1].toLowerCase();
        boolean isAscending = parseDirection(direction);
        return new SortCommand(field, isAscending);
    }

    private boolean parseDirection(String direction) throws ParseException {
        switch (direction) {
        case "asc":
            return true;
        case "desc":
            return false;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
