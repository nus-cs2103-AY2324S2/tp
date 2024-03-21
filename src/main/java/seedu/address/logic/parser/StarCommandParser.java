package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.StarCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StarCommand object.
 */
public class StarCommandParser implements Parser<StarCommand> {

    /**
     * Parses the given {@code userInput} and returns a {@code StarCommand} object.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public StarCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));
        }

        return new StarCommand(trimmedArgs);
    }
}
