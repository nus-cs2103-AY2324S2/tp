package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonDetailContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FindCommand.ACCEPTED_PREFIXES);

        Prefix prefix = extractPrefixForFindCommand(argMultimap);

        return new FindCommand(
            new PersonDetailContainsKeywordPredicate(prefix, argMultimap.getValue(prefix).get()));
    }

    /**
     * Checks if the given ArgumentMultimap contains only one valid, non-empty prefix for the FindCommand
     * and returns the found prefix.
     * @throws ParseException if the user input does not conform to the expected format
     */
    private Prefix extractPrefixForFindCommand(ArgumentMultimap argMultimap) throws ParseException {

        if (argMultimap.verifySinglePrefix()) {
            for (Prefix prefix : FindCommand.ACCEPTED_PREFIXES) {
                if (argMultimap.getValue(prefix).isPresent()
                    && !argMultimap.getValue(prefix).get().isEmpty()) {
                    return prefix;
                }
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
