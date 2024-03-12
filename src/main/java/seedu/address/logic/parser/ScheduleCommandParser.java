package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Schedule;
public class ScheduleCommandParser {

    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SCHEDULE);

        if (!arePrefixesPresent(argMultimap, PREFIX_SCHEDULE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SCHEDULE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScheduleCommand.MESSAGE_USAGE), ive);
        }

        String scheduleString = argMultimap.getValue(PREFIX_SCHEDULE).orElse("");

        try {
            Schedule schedule = convertStringToSchedule(scheduleString);
            return new ScheduleCommand(index, schedule);
        } catch (DateTimeParseException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ScheduleCommand.MESSAGE_USAGE), ive);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private Schedule convertStringToSchedule(String input) {
        LocalDateTime formattedInput = DateTimeUtil.parseStringToDateTime(input);
        return new Schedule(formattedInput);
    }
}