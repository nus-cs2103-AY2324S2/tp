package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand>{
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * @return a {@code SortCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] words = trimmedArgs.split("\\s+");

        if (words.length != 2) {
            throw new ParseException("Incorrect number of arguments. Expected format: sort <field> <asc/desc>");
        }

        String field = words[0];
        String order = words[1].toLowerCase();

        boolean isAscending = true;
        switch (order) {
        case "asc":
            isAscending = true;
            break;
        case "desc":
            isAscending = false;
            break;
        default:
            throw new ParseException("Invalid order specified. Use 'asc' for ascending or 'desc' for descending.");
        }
        return new SortCommand(field, isAscending);
    }
}
