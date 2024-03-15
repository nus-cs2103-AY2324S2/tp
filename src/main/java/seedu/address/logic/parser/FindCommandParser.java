package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GroupMatchesPredicate;
import seedu.address.model.person.EmailMatchesPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneMatchesPredicate;
import seedu.address.model.person.TagMatchesPredicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.commands.FindCommand.NOT_REQUIRED_VALUE;

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
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG, PREFIX_GROUP);

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        // Problems: Can't create Objects unless proper regex used.
        // Solution: Don't create objects
        String nameToMatch = argumentMultimap.getValue(PREFIX_NAME).orElse(NOT_REQUIRED_VALUE);
        String phoneToMatch = argumentMultimap.getValue(PREFIX_PHONE).orElse(NOT_REQUIRED_VALUE);
        String emailToMatch = argumentMultimap.getValue(PREFIX_EMAIL).orElse(NOT_REQUIRED_VALUE);
        String tagToMatch = argumentMultimap.getValue(PREFIX_TAG).orElse(NOT_REQUIRED_VALUE);
        List<String> groupToMatch = argumentMultimap.getAllValues(PREFIX_GROUP);


        String[] nameKeywords = nameToMatch.split("\\s+");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                new EmailMatchesPredicate(emailToMatch),
                new GroupMatchesPredicate(groupToMatch),
                new PhoneMatchesPredicate(phoneToMatch),
                new TagMatchesPredicate(tagToMatch));
    }

}
