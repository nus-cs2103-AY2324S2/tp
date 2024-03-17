package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParser {
    public ListCommand parse(String arguments) throws ParseException {
        if (arguments.trim().equals("-all")) {
            return new ListCommand();
        } else {
            throw new ParseException("Invalid arguments for 'view' command");
        }
    }
}
