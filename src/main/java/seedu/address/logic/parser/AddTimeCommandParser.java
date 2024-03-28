package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIMETAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTimeCommand;
import seedu.address.logic.commands.AddTimeCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.FreeTimeTag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class AddTimeCommandParser implements Parser<AddTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTimeCommand
     * and returns an AddTimeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FREETIMETAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTimeCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FREETIMETAG);

        parseFreeTimeTagsForEdit(argMultimap.getAllValues(PREFIX_FREETIMETAG)).ifPresent(editPersonDescriptor::setTags);

        return new AddTimeCommand(index, editPersonDescriptor);
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
        if (tags.size() > 2) {
            throw new ParseException(AddTimeCommand.MORE_THAN_ONE_FREETIME);
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseFreeTimeTags(tagSet));
    }

}
