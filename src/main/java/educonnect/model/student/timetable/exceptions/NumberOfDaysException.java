package educonnect.model.student.timetable.exceptions;

/**
 * Signals an invalid input to the number of days in the week.
 * Invalid input refers to less than 1 or more than 7 in a week.
 */
public class NumberOfDaysException extends IllegalArgumentException {
    private static final String MESSAGE = "Number of days cannot be less than 1 or more than 7!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
