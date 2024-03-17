package seedu.address.logic.parser;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindTagCommand object.
 */
public class FindTagCommandParser implements Parser<FindTagCommand> {

    /**
     * Parses the given {@code String} of arguments.
     * @param args the input arguments
     * @return a FindTagCommand object containing the input string
     * @throws ParseException if the user input is empty.
     */
    public FindTagCommand parse(String args) throws ParseException {
        // The following checks whether the user has entered "findtag" or "findtag " (with a space),
        // both of which are considered invalid inputs.
        if (args.isEmpty() || args.equals(" ")) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }

        // because of how AddressBookParser parses the user input, args is guaranteed to start with " ".
        // Hence we need to strip the first character to pass to the constructor.
        assert args.charAt(0) == ' ' : "args should have started with ' '";
        return new FindTagCommand(args.substring(1));
    }
}
