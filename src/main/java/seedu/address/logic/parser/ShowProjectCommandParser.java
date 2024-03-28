package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ShowProjectCommandParser implements Parser<ShowProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowProjectCommand parse(String args) throws ParseException {
        try {
            args = args.trim();
            if (args.length() == 0) {
                throw new ParseException("Please enter the project field");
            }

            System.out.println(args);
            return new ShowProjectCommand(args);
        } catch (Exception e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowProjectCommand.MESSAGE_USAGE));
        }
    }

}
