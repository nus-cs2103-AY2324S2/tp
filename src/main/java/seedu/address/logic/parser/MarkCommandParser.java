package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user inout does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        try {
            Index[] indexes = ParserUtil.parseIndexes(args);
            return new MarkCommand(indexes[0], indexes[1]);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }
    }

}
