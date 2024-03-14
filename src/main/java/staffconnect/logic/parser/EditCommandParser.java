package staffconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import staffconnect.commons.core.index.Index;
import staffconnect.logic.commands.EditCommand;
import staffconnect.logic.commands.EditCommand.EditPersonDescriptor;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.availability.Availability;
import staffconnect.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_VENUE, PREFIX_MODULE, PREFIX_TAG, PREFIX_AVAILABILITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_VENUE, PREFIX_MODULE);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editPersonDescriptor.setVenue(ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            editPersonDescriptor.setModule(ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseAvailabilitiesForEdit(argMultimap.getAllValues(PREFIX_AVAILABILITY))
                .ifPresent(editPersonDescriptor::setAvailabilities);

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
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> availabilities} into a {@code Set<Availability>}
     * if {@code availabilities} is non-empty.
     * If {@code availabilities} contain only one element which is an empty string, it will be parsed
     * into a {@code Set<Availability>} containing zero availabilities.
     */
    private Optional<Set<Availability>> parseAvailabilitiesForEdit(Collection<String> availabilities)
            throws ParseException {
        assert availabilities != null;

        if (availabilities.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> availabilitySet =
                availabilities.size() == 1 && availabilities.contains("")
                        ? Collections.emptySet()
                        : availabilities;
        return Optional.of(ParserUtil.parseAvailabilities(availabilitySet));
    }
}
