package seedu.address.model.reminder;

/**
 * Represents the type of reminder.
 */
public enum ReminderType {
    LAST_MET,
    SCHEDULES;

    @Override
    public String toString() {
        switch(this) {
        case LAST_MET:
            return "Last Met";
        case SCHEDULES:
            return "Schedules";
        default:
            throw new IllegalArgumentException();
        }
    }
}
