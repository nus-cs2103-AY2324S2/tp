package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Gender;
import seedu.address.model.person.illness.Illness;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME,
                        PREFIX_GENDER,
                        PREFIX_BIRTHDATE,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_DRUG_ALLERGY,
                        PREFIX_ILLNESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_GENDER,
                PREFIX_BIRTHDATE, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DRUG_ALLERGY);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(
                    ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            editPersonDescriptor.setGender(
                    parseGenderForEdit(
                            argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDATE).isPresent()) {
            editPersonDescriptor.setBirthDate(
                    ParserUtil.parseBirthDate(argMultimap.getValue(PREFIX_BIRTHDATE).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(
                    ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_DRUG_ALLERGY).isPresent()) {
            editPersonDescriptor.setDrugAllergy(
                    parseDrugAllergyForEdit(argMultimap.getValue(PREFIX_DRUG_ALLERGY).get()));
        }

        parseIllnessesForEdit(
                argMultimap.getAllValues(PREFIX_ILLNESS)).ifPresent(editPersonDescriptor::setIllnesses);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    private Gender parseGenderForEdit(String gender) throws ParseException {
        String genderStr = null;

        if (!gender.trim().isEmpty()) {
            genderStr = gender;
        }

        return ParserUtil.parseGender(genderStr);
    }

    private DrugAllergy parseDrugAllergyForEdit(String drugAllergy) throws ParseException {
        String drugAllergyStr = null;

        if (!drugAllergy.trim().isEmpty()) {
            drugAllergyStr = drugAllergy;
        }

        return ParserUtil.parseDrugAllergy(drugAllergyStr);
    }

    /**
     * Parses {@code Collection<String> illnesses} into a {@code Set<Illness>} if {@code illnesses} is non-empty.
     * If {@code illnesses} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Illness>} containing zero illnesses.
     */
    private Optional<Set<Illness>> parseIllnessesForEdit(Collection<String> illnesses) throws ParseException {
        assert illnesses != null;

        if (illnesses.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> illnessSet =
                illnesses.size() == 1 && illnesses.contains("") ? Collections.emptySet() : illnesses;
        return Optional.of(ParserUtil.parseIllnesses(illnessSet));
    }
}
