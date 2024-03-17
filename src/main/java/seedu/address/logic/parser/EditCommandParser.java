package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FIELD);

        Name name;
        String fieldArgs;

        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        try {
            fieldArgs = ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditPersonDescriptor editPersonDescriptor = editPersonDescription(fieldArgMultimap);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag("other");
        tags.add(tag);
        editPersonDescriptor.setTags(tags);

        return new EditCommand(name, editPersonDescriptor);
    }

    /**
     * Edits the description of a Person.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditPersonDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditPersonDescriptor editPersonDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        return editPersonDescriptor;
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
