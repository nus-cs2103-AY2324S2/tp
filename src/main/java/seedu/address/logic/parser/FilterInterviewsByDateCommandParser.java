package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_NOT_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.FilterInterviewsByDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FilterInterviewsByDateCommand object
 */
public class FilterInterviewsByDateCommandParser implements Parser<FilterInterviewsByDateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of FilterInterviewByDateCommand
     * and returns a FilterInterviewByDateCommand object for execution.
     * @throws ParseException if the user does not conform the expected date format
     */
    public FilterInterviewsByDateCommand parse(String args) throws ParseException {
        try {
            LocalDate date = LocalDate.parse(args.trim());
            return new FilterInterviewsByDateCommand(date);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_NOT_DATE));
        }

    }
}
