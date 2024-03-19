package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_RECORD;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAttendanceRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Attendance;

/**
 * Parses input arguments and creates a new AddAttendanceRecordCommand object
 */
public class AddAttendanceRecordCommandParser implements Parser<AddAttendanceRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAttendanceRecordCommand
     * and returns an AddAttendanceRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAttendanceRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_RECORD);

        if (!arePrefixesPresent(argMultimap, PREFIX_ATTENDANCE_RECORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAttendanceRecordCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ATTENDANCE_RECORD);

        Attendance attendance = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ATTENDANCE_RECORD).get());

        return new AddAttendanceRecordCommand(attendance);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
