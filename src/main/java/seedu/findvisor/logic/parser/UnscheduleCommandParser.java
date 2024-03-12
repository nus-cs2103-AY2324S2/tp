package seedu.findvisor.logic.parser;

import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.commands.UnscheduleCommand;
import seedu.findvisor.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnscheduleCommand object
 */
public class UnscheduleCommandParser implements Parser<UnscheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnscheduleCommand
     * and returns a UnscheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnscheduleCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnscheduleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnscheduleCommand.MESSAGE_USAGE), pe);
        }
    }

}
