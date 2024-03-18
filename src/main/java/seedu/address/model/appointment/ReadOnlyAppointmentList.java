package seedu.address.model.appointment;

import java.util.List;


/**
 * Unmodifiable view of an appointment list
 */
public interface ReadOnlyAppointmentList {

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointments.
     */
    List<Appointment> getAppointmentList();

}
