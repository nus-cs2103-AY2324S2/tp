package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.UnarchiveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnarchiveCommand object.
 */
public class UnarchiveCommandParser implements Parser<UnarchiveCommand> {

    @Override
    public UnarchiveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnarchiveCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnarchiveCommand.MESSAGE_USAGE), pe);
        }
    }
}
