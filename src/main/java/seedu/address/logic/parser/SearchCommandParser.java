package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.messages.SearchMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.KeywordPredicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COLLECTION;

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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COLLECTION);

        // invalid command syntax
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(SearchMessages.MESSAGE_SEARCH_INVALID_FIELD);
        }

        // check for duplicate field entries
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COLLECTION);

        return new SearchCommand(new KeywordPredicate(argMultimap));
    }
}
