package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectExamCommand object
 */
public class SelectExamCommandParser implements Parser<SelectExamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectExamCommand
     * and returns a SelectExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectExamCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectExamCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectExamCommand.MESSAGE_USAGE), pe);
        }
    }
}
