package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.house.*;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
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
        boolean HASBLOCK = true;
        boolean HASLEVEL = true;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_HOUSINGTYPE, PREFIX_LEVEL,
                        PREFIX_EMAIL, PREFIX_BLOCK, PREFIX_STREET, PREFIX_UNITNUMBER, PREFIX_POSTALCODE, PREFIX_TAG);

        //include more here for buyer/seller?
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_HOUSINGTYPE, PREFIX_LEVEL,
                PREFIX_EMAIL, PREFIX_BLOCK, PREFIX_STREET, PREFIX_UNITNUMBER, PREFIX_POSTALCODE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        UnitNumber unitNumber = ParserUtil.parseUnitNumber(argMultimap.getValue(PREFIX_UNITNUMBER).get());
        PostalCode postalCode = ParserUtil.parsePostalCode(argMultimap.getValue(PREFIX_POSTALCODE).get());
        Street street = ParserUtil.parseStreet(argMultimap.getValue(PREFIX_STREET).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        //NonLandeds might not have blocks, Landeds definitely do not have blocks.
        if (!argMultimap.getValue(PREFIX_BLOCK).isPresent()) {
            HASBLOCK = false;
        }

        //Landeds do not have levels
        if (!argMultimap.getValue(PREFIX_BLOCK).isPresent()) {
            HASLEVEL = false;
        }

        if (HASLEVEL && HASBLOCK) {
            Block block = ParserUtil.parseBlock(argMultimap.getValue(PREFIX_BLOCK).get());
            Level level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
            House house = new NonLanded(block, level, postalCode, street, unitNumber);
            Person person = new Person(name, phone, email, house, postalCode, tagList);
            return new AddCommand(person);
        } else if (HASLEVEL) {
            Level level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
            House house = new NonLanded(level, postalCode, street, unitNumber);
            Person person = new Person(name, phone, email, house, postalCode, tagList);
            return new AddCommand(person);
        }

        House house = new Landed(unitNumber, postalCode, street);

        Person person = new Person(name, phone, email, house, postalCode, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
