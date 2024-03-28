package seedu.address.model.reminder;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Represents a list of reminders.
 */
public class ReminderList {
    private ObservableList<Person> remindersList;
    private final ReminderType reminderType;

    /**
     * Instantiates a new Reminder list.
     *
     * @param reminderType  the reminder type
     * @param remindersList the reminders list
     */
    public ReminderList(ReminderType reminderType, ObservableList<Person> remindersList) {
        this.reminderType = reminderType;
        this.remindersList = remindersList;
    }

    /**
     * Returns the size of the reminder list.
     * @return size of the reminder list.
     */
    public int size() {
        return remindersList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Person p : remindersList) {
            switch (reminderType) {
            case LAST_MET:
                sb.append(p.overdueLastMetStringFormat()).append("\n");
                break;
            case SCHEDULES:
                sb.append(p.scheduleStringFormat()).append("\n");
                break;
            default:
                break;
            }
        }
        return sb.toString();
    }
}
