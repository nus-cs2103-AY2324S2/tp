package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditMaintainerCommand;
import seedu.address.logic.commands.EditMaintainerCommand.EditMaintainerDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditMaintainerCommand object
 */
public class EditMaintainerCommandParser implements Parser<EditMaintainerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMaintainerCommand
     * and returns an EditMaintainerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMaintainerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FIELD);

        Name name;
        String fieldArgs;

        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMaintainerCommand.MESSAGE_USAGE), pe);
        }

        try {
            fieldArgs = ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMaintainerCommand.MESSAGE_USAGE), pe);
        }

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_SKILL, PREFIX_COMMISSION);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKILL, PREFIX_COMMISSION);

        EditMaintainerDescriptor editMaintainerDescriptor = editMaintainerDescription(fieldArgMultimap);

        if (!editMaintainerDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMaintainerCommand.MESSAGE_NOT_EDITED);
        }

        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag("maintainer");
        tags.add(tag);
        editMaintainerDescriptor.setTags(tags);


        return new EditMaintainerCommand(name, editMaintainerDescriptor);
    }

    /**
     * Edits the description of a Maintainer.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditMaintainerDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditMaintainerDescriptor editMaintainerDescription(
                ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditMaintainerDescriptor editMaintainerDescriptor = new EditMaintainerDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editMaintainerDescriptor.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editMaintainerDescriptor.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editMaintainerDescriptor.setAddress(ParserUtil.parseAddress(
                    fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_SKILL).isPresent()) {
            editMaintainerDescriptor.setSkill(ParserUtil.parseSkill(fieldArgMultimap.getValue(PREFIX_SKILL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_COMMISSION).isPresent()) {
            editMaintainerDescriptor.setCommission(ParserUtil.parseCommission(
                    fieldArgMultimap.getValue(PREFIX_COMMISSION).get()));
        }

        return editMaintainerDescriptor;
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
