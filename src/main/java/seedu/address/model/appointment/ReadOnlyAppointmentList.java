package seedu.address.model.appointment;

import javafx.collections.ObservableList;


/**
 * Unmodifiable view of an appointment list
 */
public interface ReadOnlyAppointmentList {

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointments.
     */
    ObservableList<Appointment> getAppointmentList();

}
