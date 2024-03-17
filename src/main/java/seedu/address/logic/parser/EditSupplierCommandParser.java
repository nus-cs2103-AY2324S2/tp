package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditSupplierCommand object
 */
public class EditSupplierCommandParser implements Parser<EditSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditSupplierCommand
     * and returns an EditSupplierCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSupplierCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FIELD);

        Name name;
        String fieldArgs;

        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditSupplierCommand.MESSAGE_USAGE), pe);
        }

        try {
            fieldArgs = ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditSupplierCommand.MESSAGE_USAGE), pe);
        }

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_PRODUCT, PREFIX_PRICE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditSupplierDescriptor editSupplierDescriptor = editSupplierDescription(fieldArgMultimap);

        if (!editSupplierDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditSupplierCommand.MESSAGE_NOT_EDITED);
        }

        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag("supplier");
        tags.add(tag);
        editSupplierDescriptor.setTags(tags);


        return new EditSupplierCommand(name, editSupplierDescriptor);
    }

    /**
     * Edits the description of a Supplier.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditSupplierDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditSupplierDescriptor editSupplierDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditSupplierDescriptor editSupplierDescription = new EditSupplierDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_NAME).isPresent()) {
            editSupplierDescription.setName(ParserUtil.parseName(fieldArgMultimap.getValue(PREFIX_NAME).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editSupplierDescription.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editSupplierDescription.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editSupplierDescription.setAddress(ParserUtil.parseAddress(
                    fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_PRODUCT).isPresent()) {
            editSupplierDescription.setProduct(ParserUtil.parseProduct(
                    fieldArgMultimap.getValue(PREFIX_PRODUCT).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editSupplierDescription.setPrice(ParserUtil.parsePrice(fieldArgMultimap.getValue(PREFIX_PRICE).get()));
        }

        return editSupplierDescription;
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
