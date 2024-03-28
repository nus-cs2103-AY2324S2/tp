package educonnect.model.student.timetable.exceptions;

import educonnect.logic.parser.exceptions.ParseException;

/**
 * Signals that there are at least two or more {@code Period} objects with overlapping timeframes.
 */
public class OverlapPeriodException extends ParseException {
    private static final String MESSAGE =
            "There cannot be overlapping Periods."
            + "Ensure that each Period starts at least at the end time of the previous Period.";

    public OverlapPeriodException() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
