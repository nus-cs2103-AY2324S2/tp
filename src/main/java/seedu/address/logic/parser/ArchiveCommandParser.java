package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ArchiveCommand object
 */
public class ArchiveCommandParser implements Parser<ArchiveCommand> {

    @Override
    public ArchiveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ArchiveCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.MESSAGE_USAGE), pe);
        }
    }
}
