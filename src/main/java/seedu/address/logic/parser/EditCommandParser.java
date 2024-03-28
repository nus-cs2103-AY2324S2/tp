package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIMETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.FreeTimeTag;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    private static final Logger logger = LogsCenter.getLogger(EditCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROOMNUMBER,
                        PREFIX_TELEGRAM, PREFIX_BIRTHDAY, PREFIX_FREETIMETAG);

        Set <Index> index = new HashSet<>();

        try {
            String[] indices = argMultimap.getPreamble().trim().split("\\s+");
            for (String i : indices) {
                index.add(ParserUtil.parseIndex(i.trim()));
            }
            logger.info("Index parsed successfully");
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROOMNUMBER,
                PREFIX_TELEGRAM, PREFIX_BIRTHDAY);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            if (index.size() > 1) {
                throw new ParseException(String.format(EditCommand.MESSAGE_MULTIEDIT_FAIL, "NAME"));
            }
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            if (index.size() > 1) {
                throw new ParseException(String.format(EditCommand.MESSAGE_MULTIEDIT_FAIL, "PHONE"));
            }
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            if (index.size() > 1) {
                throw new ParseException(String.format(EditCommand.MESSAGE_MULTIEDIT_FAIL, "EMAIL"));
            }
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ROOMNUMBER).isPresent()) {
            if (index.size() > 1) {
                throw new ParseException(String.format(EditCommand.MESSAGE_MULTIEDIT_FAIL, "ROOM NUMBER"));
            }
            editPersonDescriptor.setRoomNumber(ParserUtil.parseRoomNumber(argMultimap
                    .getValue(PREFIX_ROOMNUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            if (index.size() > 1) {
                throw new ParseException(String.format(EditCommand.MESSAGE_MULTIEDIT_FAIL, "TELEGRAM"));
            }
            editPersonDescriptor.setTelegram(ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            if (index.size() > 1) {
                throw new ParseException(String.format(EditCommand.MESSAGE_MULTIEDIT_FAIL, "BIRTHDAY"));
            }
            editPersonDescriptor.setBirthday(ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get()));
        }

        parseFreeTimeTagsForEdit(argMultimap.getAllValues(PREFIX_FREETIMETAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<FreeTimeTag>> parseFreeTimeTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseFreeTimeTags(tagSet));
    }

}
