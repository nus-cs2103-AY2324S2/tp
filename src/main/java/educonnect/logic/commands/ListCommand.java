package educonnect.logic.commands;

import static java.util.Objects.requireNonNull;

import educonnect.model.Model;

/**
 * Lists all students in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of all students in the address book.\n"
            + "Optional to list all students with their timetables\n"
            + "Example 1: list\nExample 2: list timetable\n";
    public static final String MESSAGE_SUCCESS = "Listed all students";

    public static final String MESSAGE_SUCCESS_TIMETABLE = "Listed all students with timetables";

    private boolean showTimetable;

    public ListCommand() {
        this.showTimetable = false;
    }
    public ListCommand(boolean showTimeTable) {
        this.showTimetable = showTimeTable;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String outputMessage = showTimetable ? MESSAGE_SUCCESS_TIMETABLE : MESSAGE_SUCCESS;
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(outputMessage, false, false, showTimetable);
    }
}
