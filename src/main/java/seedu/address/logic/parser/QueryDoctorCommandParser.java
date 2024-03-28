package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.QueryDoctorCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new QueryDoctorCommand object
 */
public class QueryDoctorCommandParser implements Parser<QueryDoctorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QueryDoctorCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, QueryDoctorCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new QueryDoctorCommand(new DoctorContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
