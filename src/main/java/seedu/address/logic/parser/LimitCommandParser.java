package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.LimitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.library.Threshold;

/**
 * Parses input arguments and creates a new LimitCommand object
 */
public class LimitCommandParser implements Parser<LimitCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LimitCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LimitCommand parse(String args) throws ParseException {
        try {
            Threshold threshold = ParserUtil.parseThreshold(args);
            return new LimitCommand(threshold);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LimitCommand.MESSAGE_USAGE), pe);
        }
    }
}
