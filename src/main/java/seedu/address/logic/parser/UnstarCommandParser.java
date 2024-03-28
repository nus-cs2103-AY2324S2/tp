package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.UnstarCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnstarCommand object.
 */
public class UnstarCommandParser implements Parser<UnstarCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnstarCommand
     * and returns a UnstarCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public UnstarCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnstarCommand.MESSAGE_USAGE));
        }

        return new UnstarCommand(trimmedArgs);
    }
}
