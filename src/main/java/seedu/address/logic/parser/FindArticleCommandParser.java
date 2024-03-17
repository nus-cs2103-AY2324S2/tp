package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindArticleCommand object
 */
public class FindArticleCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindArticleCommand
     * and returns a FindArticleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindArticleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindArticleCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindArticleCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
