package seedu.address.logic.parser;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindTagCommandParser implements Parser<FindTagCommand> {
    public FindTagCommand parse(String args) throws ParseException {
        if (args.isEmpty() || args.equals(" ")) {
            throw new ParseException
                    (String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }
        return new FindTagCommand(args.substring(1));
    }
}
