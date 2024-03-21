package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DtagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DtagCommand object
 */
public class DtagCommandParser implements Parser<DtagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DtagCommand
     * and returns a DtagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DtagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String tagName = args.trim();
        if (tagName.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DtagCommand.MESSAGE_USAGE));
        }

        return new DtagCommand(tagName);
    }
}
