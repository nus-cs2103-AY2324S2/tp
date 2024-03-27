package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_PARAMETER_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_NAME_PARAMETER_MISSING;
import static seedu.address.logic.Messages.MESSAGE_NO_PARAMETERS;
import static seedu.address.logic.Messages.MESSAGE_PHONE_PARAMETER_MISSING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NOTE,
                        PREFIX_TAG);

        // (add)
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_NO_PARAMETERS, AddCommand.MESSAGE_USAGE));
        // (add John)
        } else if (!argMultimap.containsAll(PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_PARAMETER_FORMAT, AddCommand.MESSAGE_USAGE));
        // (add p/99898888)
        } else if (!argMultimap.contains(PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_NAME_PARAMETER_MISSING, AddCommand.MESSAGE_USAGE));
        // (add n/John)
        } else if (!argMultimap.contains(PREFIX_PHONE)) {
            throw new ParseException(String.format(MESSAGE_PHONE_PARAMETER_MISSING, AddCommand.MESSAGE_USAGE));
        // (add n/John p/98988898...)
        } else {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NOTE,
                    PREFIX_ADDRESS);

            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL));
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS));
            Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE));
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Person person = new Person(name, phone, email, address, note, tagList);

            return new AddCommand(person);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
