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
 * A list of appointmentViews that enforces uniqueness between its elements and does not allow nulls.
 * An appointmentView is considered unique by comparing using
 * {@code AppointmentView#isSameAppointment(AppointmentView)}.
 * As such, adding and updating of appointments uses AppointmentView#isSameAppointment(AppointmentView) for equality
 * to ensure that the appointmentView being added or updated is unique and not duplicated.
 * However, the removal of an appointmentView uses Person#equals(Object) to ensure that the
 * appointmentView with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see AppointmentView#isSameAppointmentView(AppointmentView)
 */
public class AppointmentViewList implements Iterable<AppointmentView> {

    private final ObservableList<AppointmentView> internalList = FXCollections.observableArrayList();
    private final ObservableList<AppointmentView> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent appointmentView as the given argument.
     */
    public boolean contains(AppointmentView toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointmentView);
    }

    /**
     * Adds an appointmentView to the list.
     * The appointmentView must not already exist in the list.
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
     * Replaces the appointmentView {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointmentView of {@code editedAppointment} must not be the same as
     * another existing appointmentView in the list.
     */
    public void setAppointmentView(AppointmentView target, AppointmentView editedAppointment) {
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
     * Removes the equivalent appointmentView from the list.
     * The appointmentView must exist in the list.
     */
    public void remove(AppointmentView toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setAppointmentViews(AppointmentViewList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        sortList();
    }

    /**
     * Replaces the contents of this list with {@code appointmentViews}.
     * {@code appointmentViews} must not contain duplicate appointmentViews.
     */
    public void setAppointmentViews(List<AppointmentView> appointments) {
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
     * Returns an AppointmentView that matches from the AppointmentView list based on
     * {@code Nric, Date, TimePeriod} given.
     * Throws an {@code AppointmentNotFoundException} if no matching appointmentView is found.
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
        if (!(other instanceof AppointmentViewList)) {
            return false;
        }

        AppointmentViewList otherAppointmentList = (AppointmentViewList) other;
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
     * Returns true if {@code appointmentViews} contains only unique appointmentViews.
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
            Comparator.comparing((AppointmentView appointmentView) -> appointmentView.getAppointment().getDate())
                .thenComparing((AppointmentView appointmentView) -> appointmentView.getAppointment().getTimePeriod()));
    }
}
