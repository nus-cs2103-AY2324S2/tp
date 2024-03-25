package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.QueryPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PatientContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new QueryPatientCommand object
 */
public class QueryPatientCommandParser implements Parser<QueryPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the QueryPatientCommand
     * and returns a QueryPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QueryPatientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, QueryPatientCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new QueryPatientCommand(new PatientContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
