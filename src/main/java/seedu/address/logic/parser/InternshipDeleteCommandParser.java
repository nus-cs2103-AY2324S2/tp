package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InternshipDeleteCommand object
 */
public class InternshipDeleteCommandParser implements InternshipParser<InternshipDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipDeleteCommand
     * and returns a InternshipDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public InternshipDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = InternshipParserUtil.parseIndex(args);
            return new InternshipDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
