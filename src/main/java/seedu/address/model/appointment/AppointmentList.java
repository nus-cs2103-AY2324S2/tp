package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all appointments in one list object.
 * Duplicates are not allowed (by {@code .isSameAppointment} comparison).
 */
public class AppointmentList implements ReadOnlyAppointmentList {
    private final UniqueAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        appointments = new UniqueAppointmentList();
    }

    /**
     * Creates an empty AppointmentList, ensure appointments to not be null.
     */
    public AppointmentList() {}
    /**
     * Creates an AppointmentList using the Appointments in the {@code toBeCopied}.
     */
    public AppointmentList(ReadOnlyAppointmentList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code AppointmentList} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentList newData) {
        requireNonNull(newData);
        setAppointments(newData.getAppointmentList());
    }

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointments.
     */
    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    /**
     * Returns true if the appointment list contains a repeated appointment as the given argument.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
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

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }
}
