package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.BankDetails;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANKDETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENTTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIRSTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FIRSTNAME, PREFIX_LASTNAME, PREFIX_PHONE,
                        PREFIX_SEX, PREFIX_EMPLOYMENTTYPE, PREFIX_ADDRESS, PREFIX_BANKDETAILS,
                        PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_FIRSTNAME, PREFIX_LASTNAME,
                PREFIX_PHONE, PREFIX_SEX, PREFIX_EMPLOYMENTTYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FIRSTNAME, PREFIX_LASTNAME, PREFIX_PHONE,
                PREFIX_SEX, PREFIX_EMPLOYMENTTYPE,
                PREFIX_ADDRESS);
        Name firstName = ParserUtil.parseName(argMultimap.getValue(PREFIX_FIRSTNAME).get());
        Name lastName = ParserUtil.parseName(argMultimap.getValue(PREFIX_LASTNAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Sex sex = ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        EmploymentType employmentType = ParserUtil.parseEmploymentType(argMultimap.getValue(PREFIX_EMPLOYMENTTYPE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(""));
        BankDetails bankDetails =
                ParserUtil.parseBankDetails(argMultimap.getValue(PREFIX_BANKDETAILS).orElse(""));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(firstName, lastName, phone, sex, employmentType, address,
                bankDetails,
                tagList);

        return new AddCommand(person);
    }

}
