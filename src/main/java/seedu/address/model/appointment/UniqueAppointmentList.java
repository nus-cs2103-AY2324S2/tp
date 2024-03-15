package seedu.address.model.appointment;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentsException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * A unique appointment is determined by {@link Appointment#equals(Appointment)}.
 *
 * Supports a minimal set of list operations.
 *
 * @see Appointment#equals(Appointment)
 */
public class UniqueAppointmentList implements Iterable<Appointment> {
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the list contains the specified appointment.
     *
     * @param toCheck The appointment to check.
     * @return True if the list contains the appointment, false otherwise.
     * @throws NullPointerException if the appointment to check is null.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an appointment to the list.
     *
     * @param toAdd The appointment to add.
     * @throws NullPointerException if the appointment is null.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);

    }

    /**
     * Replaces the person {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedAppointment} must not be the same as another existing person in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.equals(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes an appointment from the list.
     * @param toRemove The appointment to be removed.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    /**
     * Sets the appointments in the list with another list of appointments.
     *
     * @param appointments List of appointments to set.
     * @throws NullPointerException if the list of appointments is null.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentsException();
        }

        internalList.setAll(appointments);
    }

    /**
     * Returns an unmodifiable view of the list of appointments.
     *
     * @return Unmodifiable view of the list.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return An iterator.
     */
    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param other The object to compare.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueAppointmentList)) {
            return false;
        }

        UniqueAppointmentList otherUniqueAppointmentList = (UniqueAppointmentList) other;
        return internalList.equals(otherUniqueAppointmentList.internalList);
    }

    /**
     * Returns a hash code value for the list.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code appointments} contains only unique persons.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).equals(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
