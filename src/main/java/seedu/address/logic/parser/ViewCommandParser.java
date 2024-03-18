package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand or ViewCommand object depending on the arguments.
 */
public class ViewCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String arguments) throws ParseException {
        if (arguments.trim().equals("-all")) {
            return new ListCommand();
        } else if (arguments.trim().equals("-statistics")) {
            return new ViewCommand();
        } else if (arguments.trim().equals("-stats")) {
            return new ViewCommand();
        } else {
            throw new ParseException("Invalid arguments for 'view' command");
        }
    }
}
