package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_RECORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AttendanceDate;

public class AddAttendanceRecordCommand extends Command {

    public static final String COMMAND_WORD = "AddAttendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add attendance record. "
            + "Parameters: "
            + PREFIX_ATTENDANCE_RECORD + "DATE\n"
            + "Example: " + COMMAND_WORD + "ar/ 2024-03-19";

    private AttendanceDate date;


    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "AddAttendance command not implemented yet";

    public AddAttendanceRecordCommand(AttendanceDate date) {
        requireAllNonNull(date);
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_NOT_IMPLEMENTED_YET);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddAttendanceRecordCommand)) {
            return false;
        }

        AddAttendanceRecordCommand otherAddAttendanceRecordCommand = (AddAttendanceRecordCommand) other;
        return date.equals(otherAddAttendanceRecordCommand.date);
    }
}
