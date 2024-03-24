package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ReadCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new ReadCommand object
 */
public class ReadCommandParser implements Parser<ReadCommand> {
    /**
     * Parses the given {@code String} of argument in the context of the ReadCommand
     * and returns an ReadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReadCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArg = args.trim();
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_NOT_READ));
        }

        return new ReadCommand(new Nric(trimmedArg));
    }
}

