package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 * Testing
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
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CATEGORY, PREFIX_DESCRIPTION);
        //Check whether the String that is parsed in contains the following prefix.
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String category = "";
        String description;
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            if (argMultimap.getValue(PREFIX_DESCRIPTION).isEmpty()) {
                throw new ParseException(MESSAGE_INVALID_EDIT);
            }
            category = argMultimap.getValue(PREFIX_CATEGORY).get();
            editPersonDescriptor.set(category, ParserUtil.parse(category, category));
            editPersonDescriptor.setCategory(category);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
            editPersonDescriptor.set(category, ParserUtil.parse(category, description));
            editPersonDescriptor.setDescription(description);
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        // Checks if either category, description or tags are provided.
        boolean isCategoryNotSpecified = editPersonDescriptor.getCategory() == null
                || editPersonDescriptor.getCategory().isEmpty();
        boolean isDescriptionNotSpecified = editPersonDescriptor.getDescription() == null
                || editPersonDescriptor.getDescription().isEmpty();
        if (isCategoryNotSpecified && isDescriptionNotSpecified && !editPersonDescriptor.isAnyTagEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        //check if t/ is specified
        if (editPersonDescriptor.getTags().isPresent() && editPersonDescriptor.getTagSize() == 0) {
            throw new ParseException(EditCommand.MESSAGE_TAG_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
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
