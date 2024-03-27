package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_APPENDTAG;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_OVERWRITETAG;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;
import java.util.stream.Stream;

import vitalconnect.logic.commands.EditMedicalCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.AllergyTag;
import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.Weight;


/**
 * Edits the medical information of an existing person in the clinic.
 */
public class EditMedicalCommandParser implements Parser<EditMedicalCommand> {
    @Override
    public EditMedicalCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NRIC, PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_WEIGHT,
                        PREFIX_OVERWRITETAG, PREFIX_APPENDTAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMedicalCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_OVERWRITETAG);
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Height height = null;
        Weight weight = null;
        boolean overwriteTags = false;
        Set<AllergyTag> appendTags = null;

        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            height = ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get());
        }

        if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
            weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        }

        if (argMultimap.getValue(PREFIX_OVERWRITETAG).isPresent()) {
            overwriteTags = true;
        }

        if (argMultimap.getValue(PREFIX_APPENDTAG).isPresent()) {
            appendTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_APPENDTAG));
        }

        if (height == null && weight == null && !overwriteTags && appendTags == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMedicalCommand.MESSAGE_USAGE));
        }

        return new EditMedicalCommand(nric, height, weight, overwriteTags, appendTags);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
