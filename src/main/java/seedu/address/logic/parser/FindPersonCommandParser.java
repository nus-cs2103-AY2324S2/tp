package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.Arrays;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsMatchPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC);
        boolean isFindPersonByNric = argMultimap.getValue(PREFIX_NRIC).isPresent();
        boolean isFindPersonByName = argMultimap.getValue(PREFIX_NAME).isPresent();

        if (isFindPersonByNric && isFindPersonByName) {
            throw new ParseException(FindPersonCommand.MESSAGE_MULTIPLE_FIELDS_PROVIDED);
        }

        if (isFindPersonByName) {
            String trimmedNameArgs = argMultimap.getValue(PREFIX_NAME).get().trim();
            if (trimmedNameArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedNameArgs.split("\\s+");

            return new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (isFindPersonByNric) {
            String trimmedNricArgs = argMultimap.getValue(PREFIX_NRIC).get().trim();
            if (trimmedNricArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
            }
            return new FindPersonCommand(new NricContainsMatchPredicate(trimmedNricArgs));
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

}
