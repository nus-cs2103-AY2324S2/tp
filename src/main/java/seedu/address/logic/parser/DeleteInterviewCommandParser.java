package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_NOT_INTEGER;

import seedu.address.logic.commands.DeleteInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteInterviewCommand object
 */
public class DeleteInterviewCommandParser implements Parser<DeleteInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInterviewCommand parse(String args) throws ParseException {
        try {
            Integer x = Integer.valueOf(args.trim());
            x -= 1;
            return new DeleteInterviewCommand(x);
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_NOT_INTEGER));
        }
    }

}
