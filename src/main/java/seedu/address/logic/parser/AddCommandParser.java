package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.illness.Illness;

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
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NRIC,
                        PREFIX_NAME,
                        PREFIX_GENDER,
                        PREFIX_BIRTHDATE,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_DRUG_ALLERGY,
                        PREFIX_ILLNESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC,
                PREFIX_NAME, PREFIX_BIRTHDATE, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC,
                PREFIX_NAME, PREFIX_GENDER, PREFIX_BIRTHDATE, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DRUG_ALLERGY);
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Gender gender = ParserUtil.parseGender(
                argMultimap.getValue(PREFIX_GENDER).orElseGet(() -> null));
        BirthDate birthDate = ParserUtil.parseBirthDate(argMultimap.getValue(PREFIX_BIRTHDATE).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        DrugAllergy drugAllergy = ParserUtil.parseDrugAllergy(
                argMultimap.getValue(PREFIX_DRUG_ALLERGY).orElseGet(() -> "No allergy"));
        Set<Illness> illnessList = ParserUtil.parseIllnesses(argMultimap.getAllValues(PREFIX_ILLNESS));

        Person person = new Person(nric, name, gender, birthDate,
                phone, email, drugAllergy, illnessList, FXCollections.observableArrayList());

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
