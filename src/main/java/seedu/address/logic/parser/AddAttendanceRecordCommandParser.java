package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAttendanceRecordCommand;
import seedu.address.logic.commands.CreateClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AttendanceDate;
import seedu.address.model.person.Classes;

public class AddAttendanceRecordCommandParser  implements Parser<AddAttendanceRecordCommand> {

    public AddAttendanceRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_RECORD);

        if (!arePrefixesPresent(argMultimap, PREFIX_ATTENDANCE_RECORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttendanceRecordCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ATTENDANCE_RECORD);

        AttendanceDate attendanceDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ATTENDANCE_RECORD).get());

        return new AddAttendanceRecordCommand(attendanceDate);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
