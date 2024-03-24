package seedu.address.ui;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * A UI Class that encapsulates {@code RemindersCard} for Last Met and Appointments.
 * To make updating them together easier.
 */
public class RemindersPanel {

    private static final String LAST_MET_TITLE = "Last Met";
    private static final String APPOINTMENTS_TITLE = "Appointments";

    private RemindersCard lastMetCard;
    private RemindersCard appointmentsCard;

    /**
     * Creates a {@code RemindersPanel} with the given {@code }.
     */
    public RemindersPanel(ObservableList<Person> overDueLastMet) {
        this.lastMetCard = new RemindersCard(LAST_MET_TITLE, overDueLastMet);
        this.appointmentsCard = new RemindersCard(APPOINTMENTS_TITLE, overDueLastMet);
    }

    public RemindersCard getLastMetCard() {
        return lastMetCard;
    }

    public RemindersCard getAppointmentsCard() {
        return appointmentsCard;
    }

    /**
     * Updates the {@code LastMetCard} with new {@code }.
     */
    public void updateLastMetCard(ObservableList<Person> updatedOverDueList) {
        lastMetCard = new RemindersCard(LAST_MET_TITLE, updatedOverDueList);
    }

    /**
     * Updates the {@code AppointmentsCard} with new {@code }
     */
    public void updateAppointmentsCard(ObservableList<Person> updatedAppointmentsList) {
        appointmentsCard = new RemindersCard(APPOINTMENTS_TITLE, updatedAppointmentsList);
    }

    /**
     * Updates the {@code RemindersPanel} with new {@code }.
     */
    public void updateRemindersPanel(ObservableList<Person> updatedOverDueList) {
        updateLastMetCard(updatedOverDueList);
        updateAppointmentsCard(updatedOverDueList);
    }
}
