package seedu.address.ui;

import seedu.address.model.reminder.ReminderList;
import seedu.address.model.reminder.ReminderType;

/**
 * A UI Class that encapsulates {@code RemindersCard} for Last Met and Appointments.
 * To make updating them together easier.
 */
public class RemindersPanel {
    private RemindersCard lastMetCard;
    private RemindersCard scheduleCard;

    /**
     * Creates a {@code RemindersPanel} with the given {@code }.
     */
    public RemindersPanel(ReminderList overDueLastMetList, ReminderList appointmentsList) {
        this.lastMetCard = new RemindersCard(ReminderType.LAST_MET, overDueLastMetList);
        this.scheduleCard = new RemindersCard(ReminderType.SCHEDULES, appointmentsList);
    }

    public RemindersCard getLastMetCard() {
        return lastMetCard;
    }

    public RemindersCard getAppointmentsCard() {
        return scheduleCard;
    }

    /**
     * Updates the {@code LastMetCard} with new {@code }.
     */
    public void updateLastMetCard(ReminderList updatedOverDueList) {
        lastMetCard = new RemindersCard(ReminderType.LAST_MET, updatedOverDueList);
    }

    /**
     * Updates the {@code AppointmentsCard} with new {@code }
     */
    public void updateAppointmentsCard(ReminderList updatedAppointmentsList) {
        scheduleCard = new RemindersCard(ReminderType.SCHEDULES, updatedAppointmentsList);
    }

    /**
     * Updates the {@code RemindersPanel} with new {@code }.
     */
    public void updateRemindersPanel(ReminderList updatedOverDueList, ReminderList updatedAppointmentsList) {
        updateLastMetCard(updatedOverDueList);
        updateAppointmentsCard(updatedAppointmentsList);
    }
}
