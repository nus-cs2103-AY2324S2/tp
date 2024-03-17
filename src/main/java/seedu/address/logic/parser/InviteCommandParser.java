package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.InviteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InviteCommand object
 */
public class InviteCommandParser implements Parser<InviteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InviteCommand
     * and returns an InviteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InviteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new InviteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, InviteCommand.MESSAGE_USAGE), pe);
        }
    }

}
