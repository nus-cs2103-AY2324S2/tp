package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOBBY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERRED_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_PID, PREFIX_NAME, PREFIX_PREFERRED_NAME, PREFIX_FOOD_PREFERENCE,
                    PREFIX_FAMILY_CONDITION, PREFIX_HOBBY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();

        if (argMultimap.getValue(PREFIX_PID).isPresent()) {
            editPatientDescriptor.setPatientHospitalId(ParserUtil.parsePatientHospitalId(
                argMultimap.getValue(PREFIX_PID).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PREFERRED_NAME).isPresent()) {
            editPatientDescriptor.setPreferredName(ParserUtil.parsePreferredName(
                argMultimap.getValue(PREFIX_PREFERRED_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_FOOD_PREFERENCE).isPresent()) {
            editPatientDescriptor.setFoodPreference(ParserUtil.parseFoodPreference(
                argMultimap.getValue(PREFIX_FOOD_PREFERENCE).get()));
        }
        if (argMultimap.getValue(PREFIX_FAMILY_CONDITION).isPresent()) {
            editPatientDescriptor.setFamilyCondition(ParserUtil.parseFamilyCondition(
                argMultimap.getValue(PREFIX_FAMILY_CONDITION).get()));
        }
        if (argMultimap.getValue(PREFIX_HOBBY).isPresent()) {
            editPatientDescriptor.setHobby(ParserUtil.parseHobby(
                argMultimap.getValue(PREFIX_HOBBY).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPatientDescriptor::setTags);

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPatientDescriptor);
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
