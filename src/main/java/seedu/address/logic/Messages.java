package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.employee.Employee;
import seedu.address.model.task.Task;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_EMPLOYEEID = "The employee id provided is invalid";
    public static final String MESSAGE_EMPLOYEES_LISTED_OVERVIEW = "%1$d employees listed!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_TASKID = "The Task ID provided is invalid";
    public static final String MESSAGE_DUPLICATE_TASKID = "The Task ID provided is already assigned to this employee";

    public static final String MESSAGE_NONEXISTENT_TASKS = "There are no tasks assigned to this employee";
    public static final String MESSAGE_NONEXISTENT_EMPLOYEES = "There are no employees assigned to this task";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code employee} for display to the user.
     */
    public static String format(Employee employee) {
        final StringBuilder builder = new StringBuilder();
        builder.append(employee.getName())
                .append("; Phone: ")
                .append(employee.getPhone())
                .append("; Email: ")
                .append(employee.getEmail())
                .append("; Address: ")
                .append(employee.getAddress())
                .append("; Tasks Assigned: ")
                .append(employee.getTasks())
                .append("; Tags: ");
        employee.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code task} for display to the user.
     */
    public static String format(Task task) {
        final StringBuilder builder = new StringBuilder();
        builder.append(task.getName())
                .append("; TaskID: ")
                .append(task.getTaskId().taskId)
                .append("; TaskStatus: ")
                .append(task.getTaskStatus()); // Add more append if got more fields to show
        return builder.toString();
    }

}
