package seedu.address.logic.parser.person;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.person.FindPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AllFieldsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindPersonCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPersonCommand(new AllFieldsContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
