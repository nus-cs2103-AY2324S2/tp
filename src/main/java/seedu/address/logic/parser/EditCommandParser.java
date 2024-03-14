package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
        //Todo This checks for all prefix but i can change to just look for /c /d
        //Changed to accept only c/ d/ t/
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DESCRIPTION, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        //Todo check if there are duplicate prefix. can change to /c and /d only
        // done
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CATEGORY, PREFIX_DESCRIPTION);
        //here,they check whether the String that is parsed in contains the following prefix.
        String category = "";
        String description = "";
        //Todo look through all these prefixes and see whether i can just change to /c /d.
        //Changed to check for only c/ d/ t/
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            category = argMultimap.getValue(PREFIX_CATEGORY).get();
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        }
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor(category, description);
        //tag in this case is an Array list and over here, it shows how it
        //get all the values and check whether the prefix is there.
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        //Checks if there is any file edited as suggested.
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        // Checks if both category and description are not provided.
        if (editPersonDescriptor.getCategory() == null || editPersonDescriptor.getCategory().isEmpty()) {
            if (editPersonDescriptor.getDescription() == null || editPersonDescriptor.getDescription().isEmpty()) {
                throw new ParseException("Error: At least one of category (c/) or description (d/) must be provided.");
            }
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
