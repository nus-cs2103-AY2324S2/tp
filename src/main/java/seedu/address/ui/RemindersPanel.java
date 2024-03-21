package seedu.address.ui;

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
    public RemindersPanel() {
        this.lastMetCard = new RemindersCard(LAST_MET_TITLE);
        this.appointmentsCard = new RemindersCard(APPOINTMENTS_TITLE);
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
    public void updateLastMetCard() {
        lastMetCard = new RemindersCard(LAST_MET_TITLE);
    }

    /**
     * Updates the {@code AppointmentsCard} with new {@code }
     */
    public void updateAppointmentsCard() {
        appointmentsCard = new RemindersCard(APPOINTMENTS_TITLE);
    }

    /**
     * Updates the {@code RemindersPanel} with new {@code }.
     */
    public void updateRemindersPanel() {
        updateLastMetCard();
        updateAppointmentsCard();
    }
}
