package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewLoanCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewLoanCommand object
 */
public class ViewLoanCommandParser implements Parser<ViewLoanCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewLoanCommand
     * and returns a ViewLoanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewLoanCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewLoanCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewLoanCommand.MESSAGE_USAGE), pe);
        }
    }
}
