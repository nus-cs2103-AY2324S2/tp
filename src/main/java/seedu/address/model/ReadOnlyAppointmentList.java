package seedu.address.model;

import java.util.List;

import seedu.address.model.appointment.Appointment;

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
