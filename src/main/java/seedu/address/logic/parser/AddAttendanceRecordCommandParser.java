package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAttendanceRecordCommand;
import seedu.address.logic.commands.CreateClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AttendanceDate;
import seedu.address.model.person.Classes;

public class AddAttendanceRecordCommandParser {

    public AddAttendanceRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_RECORD);

        if (!arePrefixesPresent(argMultimap, PREFIX_ATTENDANCE_RECORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttendanceRecordCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ATTENDANCE_RECORD);

        String attendanceDate = argMultimap.getValue(PREFIX_ATTENDANCE_RECORD).orElse("");
        return new AddAttendanceRecordCommand(new AttendanceDate((attendanceDate)));

//        Classes classes = ParserUtil.parseClass(argMultimap.getValue(PREFIX_CLASS).get());
//        return new CreateClassCommand(classes);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
