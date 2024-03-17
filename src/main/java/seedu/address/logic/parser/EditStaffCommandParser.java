package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditStaffCommand object
 */
public class EditStaffCommandParser implements Parser<EditStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditStaffCommand
     * and returns an EditStaffCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStaffCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FIELD);

        Name name;
        String fieldArgs;

        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStaffCommand.MESSAGE_USAGE), pe);
        }

        try {
            fieldArgs = ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStaffCommand.MESSAGE_USAGE), pe);
        }

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_EMPLOYMENT, PREFIX_SALARY);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMPLOYMENT, PREFIX_SALARY);

        EditStaffDescriptor editStaffDescriptor = editStaffDescription(fieldArgMultimap);

        if (!editStaffDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStaffCommand.MESSAGE_NOT_EDITED);
        }

        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag("staff");
        tags.add(tag);
        editStaffDescriptor.setTags(tags);

        return new EditStaffCommand(name, editStaffDescriptor);
    }

    /**
     * Edits the description of a Staff.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditStaffDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditStaffDescriptor editStaffDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditStaffDescriptor editStaffDescription = new EditStaffDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStaffDescription.setName(ParserUtil.parseName(fieldArgMultimap.getValue(PREFIX_NAME).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStaffDescription.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStaffDescription.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStaffDescription.setAddress(ParserUtil.parseAddress(fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editStaffDescription.setSalary(ParserUtil.parseSalary(fieldArgMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).isPresent()) {
            editStaffDescription.setEmployment(ParserUtil.parseEmployment(
                    fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).get()));
        }

        return editStaffDescription;
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
