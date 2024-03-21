package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_INDEX, PREFIX_MEETING_INDEX, PREFIX_NAME, PREFIX_DATETIME);

        Map<Prefix, List<String>> map = argMultimap.getMap();
        /*
        for (Prefix name: map.keySet()) {
            String key = name.toString();
            String value = map.get(name).toString();
            System.out.println(key + " " + value);
        }
        */
        Index meetingIndex;
        Index clientIndex;

        try {
            meetingIndex = ParserUtil.parseMeetingIndex(argMultimap.getValue(PREFIX_MEETING_INDEX).orElse("wrong"));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        try {
            clientIndex = ParserUtil.parseClientIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).orElse("wrong"));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DATETIME, PREFIX_CLIENT);

        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor();

        if (argMultimap.getValue(PREFIX_CLIENT_INDEX).isPresent()) {
            clientIndex = ParserUtil.parseClientIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).get());
        }
        if (argMultimap.getValue(PREFIX_MEETING_INDEX).isPresent()) {
            meetingIndex = ParserUtil.parseMeetingIndex(argMultimap.getValue(PREFIX_MEETING_INDEX).get());
            //System.out.println(meetingIndex); correct
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editMeetingDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editMeetingDescriptor.setDateTime(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        EditMeetingCommand test = new EditMeetingCommand(clientIndex, editMeetingDescriptor, meetingIndex);
        return new EditMeetingCommand(clientIndex, editMeetingDescriptor, meetingIndex);
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
