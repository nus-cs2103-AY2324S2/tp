package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.AddressContainsSubstringPredicate;
import seedu.address.model.person.predicates.CombinedPredicates;
import seedu.address.model.person.predicates.EmailContainsSubstringPredicate;
import seedu.address.model.person.predicates.NameContainsSubstringPredicate;
import seedu.address.model.person.predicates.NoteContainsSubstringPredicate;
import seedu.address.model.person.predicates.PhoneContainsSubstringPredicate;
import seedu.address.model.person.predicates.TagSetContainsAnyTagPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_NOTE, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        NameContainsSubstringPredicate namePredicate = new NameContainsSubstringPredicate(
                ParserUtil.parseSearchString(argMultimap.getValue(PREFIX_NAME).orElse("")));
        PhoneContainsSubstringPredicate phonePedicate = new PhoneContainsSubstringPredicate(
                ParserUtil.parseSearchString(argMultimap.getValue(PREFIX_PHONE).orElse("")));
        EmailContainsSubstringPredicate emailPedicate = new EmailContainsSubstringPredicate(
                ParserUtil.parseSearchString(argMultimap.getValue(PREFIX_EMAIL).orElse("")));
        AddressContainsSubstringPredicate addressPedicate = new AddressContainsSubstringPredicate(
                ParserUtil.parseSearchString(argMultimap.getValue(PREFIX_ADDRESS).orElse("")));
        NoteContainsSubstringPredicate notePedicate = new NoteContainsSubstringPredicate(ParserUtil
                .parseSearchString(argMultimap.getValue(PREFIX_NOTE).orElse("")));
        TagSetContainsAnyTagPredicate tagsPredicate = new TagSetContainsAnyTagPredicate(
                ParserUtil.parseSearchTagSet(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG))));

        CombinedPredicates predicates = new CombinedPredicates(namePredicate, phonePedicate, emailPedicate,
                addressPedicate, notePedicate, tagsPredicate);

        return new FindCommand(predicates);
    }

}
