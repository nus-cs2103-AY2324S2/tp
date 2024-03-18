package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;


/**
 * Wraps all appointments in one list object.
 * Duplicates are not allowed (by {@code Appointment::equals} comparison).
 */
public class AppointmentList implements ReadOnlyAppointmentList {
    private final List<Appointment> appointments;

    /**
     * Creates an empty AppointmentList, ensure appointments to not be null.
     */
    public AppointmentList() {
        appointments = new ArrayList<>();
    }
    /**
     * Creates an AppointmentList using the Appointments in the {@code toBeCopied}.
     */
    public AppointmentList(ReadOnlyAppointmentList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void resetData(ReadOnlyAppointmentList newData) {
        requireNonNull(newData);
        appointments.clear();
        appointments.addAll(newData.getAppointmentList());
    }

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointments.
     */
    @Override
    public List<Appointment> getAppointmentList() {
        return appointments;
    }

    /**
     * Returns true if the appointment list contains a repeated appointment as the given argument.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.stream().anyMatch(appointment::equals);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentList)) {
            return false;
        }

        AppointmentList otherAppointmentList = (AppointmentList) other;
        return appointments.equals(otherAppointmentList.appointments);
    }
    /**
     * Adds an appointment to the appointment list.
     */
    public void addAppointment(Appointment appointment) {
        requireNonNull(appointment);
        appointments.add(appointment);
    }
}
