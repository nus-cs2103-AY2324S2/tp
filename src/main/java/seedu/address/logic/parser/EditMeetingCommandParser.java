package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditMeetingCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMeetingCommand
     * and returns an EditMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_INDEX, PREFIX_MEETING_INDEX,
                        PREFIX_NAME, PREFIX_DATETIME);

        Map<Prefix, List<String>> map = argMultimap.getMap();

        Index meetingIndex;
        Index clientIndex;

        try {
            meetingIndex = ParserUtil.parseMeetingIndex(argMultimap.getValue(PREFIX_MEETING_INDEX)
                    .orElse("wrong"));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        try {
            clientIndex = ParserUtil.parseClientIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).orElse("wrong"));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DATETIME, PREFIX_CLIENT);

        LocalDateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_NAME).get());
        clientIndex = ParserUtil.parseClientIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).get());
        meetingIndex = ParserUtil.parseMeetingIndex(argMultimap.getValue(PREFIX_MEETING_INDEX).get());

        EditMeetingCommand test = new EditMeetingCommand(clientIndex, meetingIndex, description, dateTime);
        return new EditMeetingCommand(clientIndex, meetingIndex, description, dateTime);
    }

}
