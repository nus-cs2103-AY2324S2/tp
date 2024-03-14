package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns a SearchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_COMMENT, PREFIX_TAG);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
        if (isEmpty(argMultimap)) {
            throw new ParseException(SearchCommand.MESSAGE_NO_FIELD_PROVIDED);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COMMENT, PREFIX_TAG);

        String[] keywords = trimmedArgs.split("\\s+");

        return new SearchCommand(new ContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

    private boolean isEmpty(ArgumentMultimap argMultimap) {
        return argMultimap.getAllValues(PREFIX_NAME).isEmpty()
                && argMultimap.getAllValues(PREFIX_PHONE).isEmpty()
                && argMultimap.getAllValues(PREFIX_EMAIL).isEmpty()
                && argMultimap.getAllValues(PREFIX_ADDRESS).isEmpty()
                && argMultimap.getAllValues(PREFIX_COMMENT).isEmpty()
                && argMultimap.getAllValues(PREFIX_TAG).isEmpty();
    }
}
