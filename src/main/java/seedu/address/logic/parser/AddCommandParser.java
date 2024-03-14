package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOBBY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
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
                ArgumentTokenizer.tokenize(args, PREFIX_PID, PREFIX_NAME, PREFIX_PRENAME, PREFIX_FOOD, PREFIX_FAMILY,
                    PREFIX_HOBBY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_PID, PREFIX_NAME, PREFIX_PRENAME, PREFIX_FOOD, PREFIX_FAMILY,
            PREFIX_HOBBY) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PRENAME, PREFIX_FOOD, PREFIX_FAMILY, PREFIX_HOBBY);
        PatientHospitalId patientHospitalId = ParserUtil.parsePatientHospitalId(argMultimap.getValue(PREFIX_PID).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        PreferredName preferredName = ParserUtil.parsePreferredName(argMultimap.getValue(PREFIX_PRENAME).get());
        FoodPreference foodPreference = ParserUtil.parseFoodPreference(argMultimap.getValue(PREFIX_FOOD).get());
        FamilyCondition familyCondition = ParserUtil.parseFamilyCondition(argMultimap.getValue(PREFIX_FAMILY).get());
        Hobby hobby = ParserUtil.parseHobby(argMultimap.getValue(PREFIX_HOBBY).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Patient patient = new Patient(patientHospitalId, name, preferredName, foodPreference, familyCondition, hobby,
            tagList);

        return new AddCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
