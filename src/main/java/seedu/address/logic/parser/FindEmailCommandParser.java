package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindEmailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEmailCommand object
 */
public class FindEmailCommandParser implements Parser<FindEmailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEmailCommand
     * and returns a FindEmailCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEmailCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmailCommand.MESSAGE_USAGE));
        }

        String[] emailKeywords = trimmedArgs.split("\\s+");

        return new FindEmailCommand(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
    }

}
