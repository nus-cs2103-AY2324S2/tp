package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.IdEqualsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagEqualsPredicate;
import seedu.address.model.person.YearJoinedEqualsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ID, CliSyntax.PREFIX_YEAR_JOINED, CliSyntax.PREFIX_TAG);

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            List<String> keywords = Arrays.asList(
                    argMultimap.getValue(CliSyntax.PREFIX_NAME).get().split("\\s+"));
            return new FindCommand(new NameContainsKeywordsPredicate(keywords));
        } else if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            return new FindCommand(new PhoneContainsKeywordsPredicate(
                    argMultimap.getAllValues(CliSyntax.PREFIX_PHONE)));
        } else if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            return new FindCommand(new EmailContainsKeywordsPredicate(
                    argMultimap.getAllValues(CliSyntax.PREFIX_EMAIL)));
        } else if (argMultimap.getValue(CliSyntax.PREFIX_ID).isPresent()) {
            return new FindCommand(new IdEqualsPredicate(
                    argMultimap.getValue(CliSyntax.PREFIX_ID).get()));
        } else if (argMultimap.getValue(CliSyntax.PREFIX_YEAR_JOINED).isPresent()) {
            return new FindCommand(new YearJoinedEqualsPredicate(
                    argMultimap.getValue(CliSyntax.PREFIX_YEAR_JOINED).get()));
        } else if (argMultimap.getValue(CliSyntax.PREFIX_TAG).isPresent()) {
            return new FindCommand(new TagEqualsPredicate(
                    argMultimap.getValue(CliSyntax.PREFIX_TAG).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
