package seedu.address.logic.parser;

import seedu.address.logic.commands.PinCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NusId;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


/**
 * Parses input arguments and creates a new PinCommand object
 */
public class PinCommandParser implements Parser<PinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PinCommand
     * and returns a PinCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PinCommand parse(String args) throws ParseException {
        try {
            NusId nusId = ParserUtil.parseNusId(args);
            return new PinCommand(nusId);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE), pe);
        }
    }
}
