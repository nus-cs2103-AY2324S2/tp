package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_ALLERGYTAG;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;
import java.util.stream.Stream;

import vitalconnect.logic.commands.AddContactCommand;
import vitalconnect.logic.commands.AddMedInfoCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.allergytag.AllergyTag;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.model.person.medicalinformation.Weight;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddMedInfoCommandParser implements Parser<AddMedInfoCommand> {
    @Override
    public AddMedInfoCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NRIC, PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_ALLERGYTAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC, PREFIX_HEIGHT, PREFIX_WEIGHT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedInfoCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_HEIGHT, PREFIX_WEIGHT);
        Height height = ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get());
        Weight weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        Set<AllergyTag> allergyTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_ALLERGYTAG));
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());

        // Check if at least one of the fields is present
        if (height.isEmpty() && weight.isEmpty() && Nric.isValidNric(nric.toString())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
        }

        MedicalInformation medicalInformation = new MedicalInformation(height, weight, allergyTags);

        return new AddMedInfoCommand(nric, medicalInformation);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
