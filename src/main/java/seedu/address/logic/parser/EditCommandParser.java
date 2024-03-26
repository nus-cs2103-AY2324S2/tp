package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANKDETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIRSTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYRATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FIRSTNAME, PREFIX_LASTNAME, PREFIX_PHONE,
                    PREFIX_ADDRESS, PREFIX_TAG, PREFIX_SEX, PREFIX_PAYRATE, PREFIX_BANKDETAILS);

        Phone number;

        try {
            number = ParserUtil.parsePhone(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FIRSTNAME, PREFIX_LASTNAME, PREFIX_PHONE, PREFIX_SEX,
                PREFIX_PAYRATE, PREFIX_BANKDETAILS, PREFIX_ADDRESS);

        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_FIRSTNAME).isPresent()) {
            editPersonDescriptor.setFirstName(ParserUtil.parseName(argMultimap.getValue(PREFIX_FIRSTNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_LASTNAME).isPresent()) {
            editPersonDescriptor.setLastName(ParserUtil.parseName(argMultimap.getValue(PREFIX_LASTNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_SEX).isPresent()) {
            editPersonDescriptor.setSex(ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get()));
        }
        if (argMultimap.getValue(PREFIX_PAYRATE).isPresent()) {
            editPersonDescriptor.setPayRate(ParserUtil
                .parsePayRate(argMultimap.getValue(PREFIX_PAYRATE).get()));
        }
        if (argMultimap.getValue(PREFIX_BANKDETAILS).isPresent()) {
            editPersonDescriptor.setBankDetails(ParserUtil
                .parseBankDetails(argMultimap.getValue(PREFIX_BANKDETAILS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(number, editPersonDescriptor);
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
