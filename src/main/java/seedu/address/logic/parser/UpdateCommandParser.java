package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.commands.UpdateCommand.UpdatePersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class UpdateCommandParser implements Parser<UpdateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS,
                        PREFIX_DATEOFBIRTH, PREFIX_SEX, PREFIX_STATUS, PREFIX_TAG, PREFIX_EMAIL, PREFIX_COUNTRY,
                        PREFIX_DATEOFADMISSION, PREFIX_ALLERGIES, PREFIX_BLOODTYPE, PREFIX_CONDITION, PREFIX_SYMPTOM,
                        PREFIX_DIAGNOSIS);

        Nric nric;

        try {
            nric = ParserUtil.parseNric(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS,
                PREFIX_DATEOFBIRTH, PREFIX_SEX, PREFIX_STATUS, PREFIX_TAG, PREFIX_EMAIL, PREFIX_COUNTRY,
                PREFIX_DATEOFADMISSION, PREFIX_ALLERGIES, PREFIX_BLOODTYPE, PREFIX_CONDITION, PREFIX_SYMPTOM,
                PREFIX_DIAGNOSIS);

        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setNric(nric);

        // Mandatory fields
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            updatePersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            updatePersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            updatePersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DATEOFBIRTH).isPresent()) {
            updatePersonDescriptor.setDateOfBirth(
                    ParserUtil.parseDateOfBirth(argMultimap.getValue(PREFIX_DATEOFBIRTH).get()));
        }
        if (argMultimap.getValue(PREFIX_SEX).isPresent()) {
            updatePersonDescriptor.setSex(ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            updatePersonDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }

        // Data fields
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            updatePersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_COUNTRY).isPresent()) {
            updatePersonDescriptor.setCountry(ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get()));
        }

        // Medical information
        if (argMultimap.getValue(PREFIX_ALLERGIES).isPresent()) {
            updatePersonDescriptor.setAllergies(
                    ParserUtil.parseAllergies(argMultimap.getValue(PREFIX_ALLERGIES).get()));
        }
        if (argMultimap.getValue(PREFIX_BLOODTYPE).isPresent()) {
            updatePersonDescriptor.setBloodType(
                    ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOODTYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_CONDITION).isPresent()) {
            updatePersonDescriptor.setCondition(
                    ParserUtil.parseCondition(argMultimap.getValue(PREFIX_CONDITION).get()));
        }
        if (argMultimap.getValue(PREFIX_DATEOFADMISSION).isPresent()) {
            updatePersonDescriptor.setDateOfAdmission(
                    ParserUtil.parseDateOfAdmission(argMultimap.getValue(PREFIX_DATEOFADMISSION).get()));
        }
        if (argMultimap.getValue(PREFIX_DIAGNOSIS).isPresent()) {
            updatePersonDescriptor.setDiagnosis(
                    ParserUtil.parseDiagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).get()));
        }
        if (argMultimap.getValue(PREFIX_SYMPTOM).isPresent()) {
            updatePersonDescriptor.setSymptom(ParserUtil.parseSymptom(argMultimap.getValue(PREFIX_SYMPTOM).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(updatePersonDescriptor::setTags);

        if (!updatePersonDescriptor.isAnyFieldUpdated()) {
            throw new ParseException(UpdateCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateCommand(nric, updatePersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
