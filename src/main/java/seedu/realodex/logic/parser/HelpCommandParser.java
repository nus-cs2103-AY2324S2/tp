package seedu.realodex.logic.parser;

import seedu.realodex.logic.commands.HelpCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String command) throws ParseException {
        command = command.trim();
        return new HelpCommand(command);
    }
}
