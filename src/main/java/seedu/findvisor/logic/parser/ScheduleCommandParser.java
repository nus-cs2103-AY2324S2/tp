package seedu.findvisor.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_MEETING_REMARK;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.commons.util.DateTimeUtil;
import seedu.findvisor.logic.commands.ScheduleCommand;
import seedu.findvisor.logic.parser.exceptions.ParseException;
import seedu.findvisor.model.person.Meeting;

/**
 * Parses input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns an ScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATETIME, PREFIX_END_DATETIME, PREFIX_MEETING_REMARK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_DATETIME, PREFIX_END_DATETIME);

        if (!(argMultimap.getValue(PREFIX_START_DATETIME).isPresent()
                && argMultimap.getValue(PREFIX_END_DATETIME).isPresent())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = DateTimeUtil.parseDateTimeString(argMultimap.getValue(PREFIX_START_DATETIME).get());
            endDateTime = DateTimeUtil.parseDateTimeString(argMultimap.getValue(PREFIX_END_DATETIME).get());
        } catch (DateTimeParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        String remark = argMultimap.getValue(PREFIX_MEETING_REMARK).orElse("");

        Meeting meeting = new Meeting(startDateTime, endDateTime, remark);

        return new ScheduleCommand(index, meeting);
    }
}
