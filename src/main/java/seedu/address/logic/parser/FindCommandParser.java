package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.MatchingOrderIndexPredicate;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.MatchingEmailPredicate;
import seedu.address.model.person.MatchingPhonePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE + "\n"
                            + FindOrderCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_ORDER);

        argMultimap.verifyOnlyOnePrefixFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_ORDER);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> names = argMultimap.getAllValues(PREFIX_NAME);
            for (int i = 0; i < names.size(); i++) {
                String parsedName = ParserUtil.parseName(names.get(i)).fullName;
                names.set(i, parsedName);
            }
            return new FindPersonCommand(new NameContainsKeywordsPredicate(names));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            List<String> phoneNumbers = argMultimap.getAllValues(PREFIX_PHONE);
            for (int i = 0; i < phoneNumbers.size(); i++) {
                String parsedPhoneNumber = ParserUtil.parsePhone(phoneNumbers.get(i)).value;
                phoneNumbers.set(i, parsedPhoneNumber);
            }
            return new FindPersonCommand(new MatchingPhonePredicate(phoneNumbers));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            List<String> emails = argMultimap.getAllValues(PREFIX_EMAIL);
            for (int i = 0; i < emails.size(); i++) {
                String parsedEmail = ParserUtil.parseEmail(emails.get(i)).value;
                emails.set(i, parsedEmail);
            }
            return new FindPersonCommand(new MatchingEmailPredicate(emails));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> addresses = argMultimap.getAllValues(PREFIX_ADDRESS);
            for (int i = 0; i < addresses.size(); i++) {
                String parsedAddress = ParserUtil.parseAddress(addresses.get(i)).value;
                addresses.set(i, parsedAddress);
            }
            return new FindPersonCommand(new AddressContainsKeywordsPredicate(addresses));
        }

        return new FindOrderCommand(new MatchingOrderIndexPredicate((argMultimap.getAllValues(PREFIX_ORDER))));
    }
}
