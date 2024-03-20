package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Name;



/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * An appointment is considered unique by comparing using {@code Appointment#isSameAppointment(Appointment)}.
 * As such, adding and updating of appointments uses Appointment#isSameAppointment(Appointment) for equality
 * to ensure that the appointment being added or updated is unique and not duplicated.
 * However, the removal of an appointment uses Person#equals(Object) to ensure that the
 * appointment with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see AppointmentView#isSameAppointmentView(AppointmentView)
 */
public class AppointmentListView implements Iterable<AppointmentView> {

    private final ObservableList<AppointmentView> internalList = FXCollections.observableArrayList();
    private final ObservableList<AppointmentView> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(AppointmentView toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointmentView);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not already exist in the list.
     */
    public void add(AppointmentView toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
        sortList();
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment of {@code editedAppointment} must not be the same as another existing appointment in the list.
     */
    public void setAppointment(AppointmentView target, AppointmentView editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.isSameAppointmentView(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
        sortList();
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(AppointmentView toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setAppointments(AppointmentListView replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        sortList();
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<AppointmentView> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentViewsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
        sortList();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<AppointmentView> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<AppointmentView> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns an Appointment that matches from the Appointment list based on {@code Nric, Date, TimePeriod} given.
     * Throws an {@code AppointmentNotFoundException} if no matching appointment is found.
     */
    public AppointmentView getMatchingAppointment(Name name, Appointment appointment) {
        requireNonNull(name);
        requireNonNull(appointment);

        for (AppointmentView appointmentView : this) {
            if (appointmentView.getName().equals(name)
                    && appointmentView.getAppointment().equals(appointment)) {
                return appointmentView;
            }
        }

        throw new AppointmentNotFoundException();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentListView)) {
            return false;
        }

        AppointmentListView otherAppointmentList = (AppointmentListView) other;
        return internalList.equals(otherAppointmentList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code appointments} contains only unique appointments.
     */
    private boolean appointmentViewsAreUnique(List<AppointmentView> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointmentView(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sort internal list according to date and time
     */
    private void sortList() {
        internalList.sort(
            Comparator.comparing(
                (AppointmentView appointmentView) -> appointmentView.getAppointment().getDate())
                .thenComparing(
                    (AppointmentView appointmentView) -> appointmentView.getAppointment().getTimePeriod()));
    }
}
