package seedu.findvisor.logic.parser;

import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Stream;

import seedu.findvisor.logic.commands.FindCommand;
import seedu.findvisor.logic.parser.exceptions.ParseException;
import seedu.findvisor.model.person.EmailContainsKeywordPredicate;
import seedu.findvisor.model.person.NameContainsKeywordPredicate;
import seedu.findvisor.model.person.PhoneContainsKeywordPredicate;
import seedu.findvisor.model.tag.TagsContainsKeywordsPredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        if (!isSinglePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameKeyword = argMultimap.getValue(PREFIX_NAME).get();
            return new FindCommand(new NameContainsKeywordPredicate(nameKeyword));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String emailKeyword = argMultimap.getValue(PREFIX_EMAIL).get();
            return new FindCommand(new EmailContainsKeywordPredicate(emailKeyword));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneKeyword = argMultimap.getValue(PREFIX_PHONE).get();
            return new FindCommand(new PhoneContainsKeywordPredicate(phoneKeyword));
        }

        List<String> tagsKeywords = argMultimap.getAllValues(PREFIX_TAG);
        return new FindCommand(new TagsContainsKeywordsPredicate(tagsKeywords));
    }

    /**
     * Returns true if exactly one prefix is present in the given {@code ArgumentMultimap}.
     * @param argMultimap The {@link ArgumentMultimap} to check for the presence of prefixes.
     * @param prefixes A varargs array of {@link Prefix} objects to be checked in the {@code argMultimap}.
     * @return {@code true} if exactly one of the specified prefixes is present in the {@code ArgumentMultimap}.
     */
    private boolean isSinglePrefixPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        long prefixCount = Stream.of(prefixes)
                .filter(prefix -> argMultimap.getValue(prefix).isPresent())
                .count();
        return prefixCount == 1;
    }
}
