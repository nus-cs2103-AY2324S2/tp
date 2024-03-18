package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AvailableAtDatePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_AVAIL, PREFIX_TAG);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_AVAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(predicateBuilder(argMultimap, PREFIX_NAME, PREFIX_AVAIL));
    }

    /**
     * Returns true if at least one of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private Predicate<Person> predicateBuilder(ArgumentMultimap argMultimap, Prefix... prefixes) throws ParseException {
        Predicate<Person> predicate = null;
        for (Prefix prefix : prefixes) {
            String keywords = argMultimap.getValue(prefix).orElse("").trim();
            if (!keywords.isEmpty()) {
                switch (prefix.toString()) {
                case "n/":
                    if (predicate == null) {
                        predicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords.split("\\s+")));
                    } else {
                        predicate = predicate.or(
                            new NameContainsKeywordsPredicate(Arrays.asList(keywords.split("\\s+"))));
                    }
                    break;
                case "a/":
                    if (predicate == null) {
                        predicate = new AvailableAtDatePredicate(Arrays.asList(keywords.split("\\s+")));
                    } else {
                        predicate = predicate.or(new AvailableAtDatePredicate(Arrays.asList(keywords.split("\\s+"))));
                    }
                    break;
                default:
                    throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
            }
        };

        if (predicate == null) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return predicate;
    }

}
