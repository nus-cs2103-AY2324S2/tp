package educonnect.model.student.timetable.exceptions;

/**
 * Signals an invalid {@code Period} being created.
 * Invalid period can be from empty names, or when the end time is earlier than the start time.
 */
public class InvalidPeriodException extends IllegalArgumentException {
    private static final String MESSAGE = "Period name cannot be blank, "
                                          + "or end time should not be earlier than start time!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
