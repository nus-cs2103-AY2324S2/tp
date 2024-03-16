package seedu.address.logic.parser;

import seedu.address.logic.commands.SearchNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameKeywordPredicate;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchNameCommandParser implements Parser<SearchNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns a SearchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchNameCommand.MESSAGE_USAGE));
        }

        String nameKeyword = trimmedArgs;

        return new SearchNameCommand(new NameKeywordPredicate(nameKeyword));
    }

}
