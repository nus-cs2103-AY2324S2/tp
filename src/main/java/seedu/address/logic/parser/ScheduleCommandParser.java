package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Meeting;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns an ScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATETIME, PREFIX_END_DATETIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_DATETIME, PREFIX_END_DATETIME);

        if (!(argMultimap.getValue(PREFIX_START_DATETIME).isPresent()
                && argMultimap.getValue(PREFIX_END_DATETIME).isPresent())) {
            throw new ParseException(ScheduleCommand.MESSAGE_NOT_EDITED);
        }

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = parseDateTime(argMultimap.getValue(PREFIX_START_DATETIME).get());
            endDateTime = parseDateTime(argMultimap.getValue(PREFIX_END_DATETIME).get());
        } catch (DateTimeParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        Meeting meeting = new Meeting(startDateTime, endDateTime);

        return new ScheduleCommand(index, meeting);
    }

    /**
     * Converts a String into a LocalDateTime object. The expected format is yyyy-MM-dd'T'HH:mm. For
     * example, 2023-01-29T14:00.
     *
     * @param input The string to be converted to a LocalDateTime object.
     * @return The resulting LocalDateTime object from the conversion.
     * @throws DateTimeParseException If the String is not in the expected format.
     */
    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DATE_TIME_FORMAT);
    }
}
