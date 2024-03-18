package seedu.address.model;

import seedu.address.model.appointment.Appointment;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class AppointmentList implements ReadOnlyAppointmentList{
    private final List<Appointment> appointments;

    public AppointmentList() {
        appointments = new ArrayList<>();
    }

    public AppointmentList(ReadOnlyAppointmentList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

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

    /**
     * Adds an appointment to the appointment list.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
