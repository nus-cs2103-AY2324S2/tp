package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFADMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Status;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS,
                        PREFIX_DATEOFBIRTH, PREFIX_SEX, PREFIX_STATUS, PREFIX_TAG, PREFIX_EMAIL, PREFIX_COUNTRY,
                        PREFIX_DATEOFADMISSION, PREFIX_ALLERGIES, PREFIX_BLOODTYPE, PREFIX_CONDITION, PREFIX_SYMPTOM,
                        PREFIX_DIAGNOSIS);
        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_DATEOFBIRTH,
                PREFIX_SEX, PREFIX_STATUS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS,
                PREFIX_DATEOFBIRTH, PREFIX_SEX);
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        DateOfBirth dob = ParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DATEOFBIRTH).get());
        Sex sex = ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        //TODO (later): assersion to make sure optinal values don't generate errors
        Person person = new Person(nric, name, phone, address, dob, sex, status);
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            person.setEmail(email);
        }
        if (argMultimap.getValue(PREFIX_COUNTRY).isPresent()) {
            person.setCountry(ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get()));
        }
        if (argMultimap.getValue(PREFIX_DATEOFADMISSION).isPresent()) {
            person.setDateOfAdmission(ParserUtil.parseDateOfAdmission(argMultimap
                    .getValue(PREFIX_DATEOFADMISSION).get()));
        }
        if (argMultimap.getValue(PREFIX_ALLERGIES).isPresent()) {
            person.setAllergies(ParserUtil.parseAllergies(argMultimap.getValue(PREFIX_ALLERGIES).get()));
        }
        if (argMultimap.getValue(PREFIX_BLOODTYPE).isPresent()) {
            person.setBloodType(ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOODTYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_CONDITION).isPresent()) {
            person.setCondition(ParserUtil.parseCondition(argMultimap.getValue(PREFIX_CONDITION).get()));
        }
        if (argMultimap.getValue(PREFIX_SYMPTOM).isPresent()) {
            person.setSymptom(ParserUtil.parseSymptom(argMultimap.getValue(PREFIX_SYMPTOM).get()));
        }
        if (argMultimap.getValue(PREFIX_DIAGNOSIS).isPresent()) {
            person.setDiagnosis(ParserUtil.parseDiagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).get()));
        }

        return new CreateCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
