package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddSchedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.Schedule;

/**
 * Parses input arguments and creates a new AddSchedCommandParser object
 */
public class AddSchedCommandParser implements Parser<AddSchedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSchedCommand
     * and returns a AddSchedCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddSchedCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_SCHEDULE, PREFIX_START, PREFIX_END);

        ArrayList<Index> indexArrayList;
        try {
            indexArrayList = ParserUtil.parseIndexArrayList(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSchedCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_SCHEDULE, PREFIX_START, PREFIX_END)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSchedCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SCHEDULE, PREFIX_START, PREFIX_END);
        String schedName = argMultimap.getValue(PREFIX_SCHEDULE).get();
        try {
            LocalDateTime startTime = LocalDateTime.parse(argMultimap.getValue(PREFIX_START).get(),
                    Schedule.CUSTOM_DATETIME);
            LocalDateTime endTime = LocalDateTime.parse(argMultimap.getValue(PREFIX_END).get(),
                    Schedule.CUSTOM_DATETIME);
            Schedule schedule = new Schedule(schedName, startTime, endTime);

            return new AddSchedCommand(indexArrayList, schedule);
        } catch (DateTimeException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSchedCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
