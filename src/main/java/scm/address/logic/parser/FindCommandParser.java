package scm.address.logic.parser;

import static scm.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static scm.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import scm.address.logic.commands.AddCommand;
import scm.address.logic.commands.FindCommand;
import scm.address.logic.parser.exceptions.ParseException;
import scm.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private final Prefix[] PREFIXES = { PREFIX_NAME, PREFIX_ADDRESS, PREFIX_TAG };

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_TAG);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_TAG);

        List<String> nameKeywords = getKeywords(argMultimap, PREFIX_NAME);
        List<String> addressKeywords = getKeywords(argMultimap, PREFIX_ADDRESS);
        List<String> tagKeywords = getKeywords(argMultimap, PREFIX_TAG);

        return new FindCommand(new NameContainsKeywordsPredicate(nameKeywords));
    }

    /**
     * Returns true if at least one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private List<String> getKeywords(ArgumentMultimap argMultimap, Prefix prefix) {
        if (argMultimap.getValue(prefix).isEmpty()) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(argMultimap.getValue(prefix).get().split("\\s+"));
        }
    }
}
