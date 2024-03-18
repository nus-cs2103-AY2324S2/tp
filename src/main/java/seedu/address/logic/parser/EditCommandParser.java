package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStartupDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
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
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_INDUSTRY, CliSyntax.PREFIX_FUNDING_STAGE, CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
            CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ADDRESS);

        EditStartupDescriptor editStartupDescriptor = new EditStartupDescriptor();

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editStartupDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_FUNDING_STAGE).isPresent()) {
            editStartupDescriptor.setFundingStage(
                ParserUtil.parseFundingStage(argMultimap.getValue(CliSyntax.PREFIX_FUNDING_STAGE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY).isPresent()) {
            editStartupDescriptor.setIndustry(
                ParserUtil.parseIndustry(argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            editStartupDescriptor.setPhone(
                ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            editStartupDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            editStartupDescriptor.setAddress(ParserUtil.parseAddress(
                argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG)).ifPresent(editStartupDescriptor::setTags);

        if (!editStartupDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStartupDescriptor);
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
